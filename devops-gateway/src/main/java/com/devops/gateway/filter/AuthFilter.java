package com.devops.gateway.filter;

import com.devops.api.entity.Menu;
import com.devops.api.query.CachePermFeignApi;
import com.devops.api.query.QueryPermFeignApi;
import com.devops.base.common.Constant;
import com.devops.base.common.ErrorCode;
import com.devops.base.common.Result;
import com.devops.base.utils.JWTUtil;
import com.devops.base.utils.JacksonUtil;
import com.google.common.net.HttpHeaders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.net.MediaType.JSON_UTF_8;

/**
 * @author: liushuai
 * @date: 2020/10/18
 * @description：
 */
@Slf4j
public class AuthFilter implements Ordered, GlobalFilter {


    @Resource
    private CachePermFeignApi cachePermFeignApi;
    @Resource
    private QueryPermFeignApi queryPermFeignApi;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Result<List<Menu>> cachePermResult = cachePermFeignApi.selectMenusByCache();
        if (!cachePermResult.getSuccess()) {
            log.error("调用cachePermFeignApi.selectMenusByCache()接口失败，错误信息为:{}", cachePermResult.getErrorMessage());
            exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
            return getVoidMono(exchange.getResponse(), ErrorCode.SYSTEM_ERROR);
        }
        List<Menu> cachePermList = cachePermResult.getObj();
        if (CollectionUtils.isEmpty(cachePermList)) {
            log.error("缓存中权限数据为空");
            return getVoidMono(exchange.getResponse(), ErrorCode.CACHE_PERM_IS_NULL);
        }
        ServerHttpRequest request = exchange.getRequest();
        String requestType = request.getMethodValue();
        String requestUri = request.getURI().getPath();
        List<String> cacheRequestUriList = cachePermList.stream()
                .filter(i -> i.getRequestType().equals(requestType))
                .map(Menu::getRequestUri)
                .collect(Collectors.toList());

        if (CollectionUtils.isEmpty(cacheRequestUriList)) {
            return chain.filter(exchange);
        }
        if (!cacheRequestUriList.contains(requestUri)) {
            //不需要权限的接口
            return chain.filter(exchange);
        }
        String token = exchange.getRequest().getHeaders().getFirst(Constant.USER_TOKEN);
        Integer uid = JWTUtil.getUid(token);
        //用户拥有的权限
        Result<List<Menu>> permResult = queryPermFeignApi.selectPermListByUid(uid);
        if (!permResult.getSuccess()) {
            log.error("调用queryPermFeignApi.selectPermListByUid()接口失败，错误信息为:{}", permResult.getErrorMessage());
            return getVoidMono(exchange.getResponse(), ErrorCode.SYSTEM_ERROR);
        }
        //用户拥有的权限
        List<Menu> permList = permResult.getObj();
        if (CollectionUtils.isEmpty(permList)) {
            return getVoidMono(exchange.getResponse(), ErrorCode.PERM_DENIED);
        }
        //用户当前请求所拥有的requestUri
        List<String> ownRequestUriList = permList.stream()
                .filter(i -> i.getRequestType().equals(requestType))
                .map(Menu::getRequestUri)
                .collect(Collectors.toList());

        if (!ownRequestUriList.contains(requestUri)) {
            return getVoidMono(exchange.getResponse(), ErrorCode.PERM_DENIED);
        }
        return chain.filter(exchange);
    }

    private Mono<Void> getVoidMono(ServerHttpResponse serverHttpResponse, ErrorCode errorCode) {
        serverHttpResponse.getHeaders().add(HttpHeaders.CONTENT_TYPE, JSON_UTF_8.toString());
        DataBuffer dataBuffer = serverHttpResponse.bufferFactory().wrap(JacksonUtil.toJSon(new Result<>(errorCode)).getBytes());
        return serverHttpResponse.writeWith(Flux.just(dataBuffer));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
