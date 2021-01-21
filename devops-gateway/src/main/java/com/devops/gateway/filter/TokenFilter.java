package com.devops.gateway.filter;

import com.devops.base.common.Constant;
import com.devops.base.common.ErrorCode;
import com.devops.base.common.Result;
import com.devops.base.utils.JacksonUtil;
import com.google.common.net.HttpHeaders;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.google.common.net.MediaType.JSON_UTF_8;

/**
 * @author: liushuai
 * @date: 2020/10/17
 * @description：
 */
@Slf4j
public class TokenFilter implements GlobalFilter, Ordered {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        //指定的url放行
        String path = request.getURI().getPath();
        if ("/mvc/mvc/login".equals(path) || "/mvc/mvc/verify/code".equals(path)) {
            return chain.filter(exchange);
        }
        String token = exchange.getRequest().getHeaders().getFirst(Constant.USER_TOKEN);
        if (StringUtils.isBlank(token)) {
            log.error("token不能为空");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return getVoidMono(exchange.getResponse(), ErrorCode.TOKEN_IS_NULL);
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
        return -100;
    }
}
