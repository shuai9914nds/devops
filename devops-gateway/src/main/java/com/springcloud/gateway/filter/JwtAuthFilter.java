//import cn.hutool.core.util.StrUtil;
//import com.alibaba.fastjson.JSON;
//import com.juyuansoft.constant.SecurityConstants;
//import com.juyuansoft.security.JyAuthHeaders;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.JwtException;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import com.juyuansoft.core.Result;
//import com.juyuansoft.core.ResultCode;
//import com.juyuansoft.uitl.JwtUtils;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.core.io.buffer.DataBufferFactory;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//@Slf4j
//@Component
//@AllArgsConstructor
//public class JwtAuthFilter implements GlobalFilter, Ordered {
//
//    //Jwt工具类
//    JwtUtils jwtUtils;
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        //其中ServerWebExchange是Spring封装了javaweb中的相关的对象比如request和response,session等对象
//        //获取http请求对象
//        ServerHttpRequest request = exchange.getRequest();
//        // 如果请求头中已存在用户权限信息直接放行
//        if (StrUtil.isNotEmpty(request.getHeaders().getFirst(JyAuthHeaders.AUTH_USER_JSON))) {
//            //放行
//            return chain.filter(exchange);
//        }
//        //获取请求的url
//        String path = request.getURI().getPath();
//        //静态资源访问,接口通过判断url中是否含有指定的字符来判断是否放行,在这里设置url的白名单
//        if(StrUtil.containsAnyIgnoreCase(path,SecurityConstants.ACC_MEDIA_URL)){
//            return chain.filter(exchange);
//        }
////如果上面判断没有放行就执行到这里进行登陆的判断
//        ServerHttpResponse response = exchange.getResponse();
//
//        Result result = new Result();
//        //获取请求头中的token信息
//        String jws = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
//        //如果获取出来的token为空就返回未登录的Response响应
//        if (StrUtil.isEmpty(jws)) {
//            result.setCode(ResultCode.UNAUTHORIZED).setMessage("用户未登录");
//            return responseResult(response, result);
//        }
//        //如果取出来token了就使用jwt工具类进行token信息的解析
//        try {
//            //尝试进行解析如果解析过程不报错,就代表这个token有效
//            Claims claims = jwtUtils.parseAccessJwt(jws);
//            //进行请求的再次封装
//            ServerHttpRequest mutateRequest = request.mutate()
//                    .header(JyAuthHeaders.AUTH_USER_JSON, claims.get(JyAuthHeaders.AUTH_USER_JSON, String.class))
//                    .build();
//            ServerWebExchange mutateExchange = exchange.mutate()
//                    .request(mutateRequest)
//                    .build();
//            //让当前请求通过这个过滤器,进入下一个过滤器
//            return chain.filter(mutateExchange);
//        } catch (JwtException e) {
//            //如果解析失败,就返回登陆已过期的响应
//            log.error("登录已过期", e);
//            result.setCode(ResultCode.UNAUTHORIZED).setMessage("登录已过期");
//            //返回失败结果
//            return responseResult(response, result);
//        }
//    }
//
//    @Override
//    public int getOrder() {
//        //这个返回值就是在当前过滤器对象在过滤器执行链中的执行顺序,值越小越先执行
//        return -100;
//    }
//
//    //对请求失败结果进行封装
//    private Mono<Void> responseResult(ServerHttpResponse response, Result result) {
//        response.getHeaders().add(HttpHea77ders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);hhh//        response.setStatusCode(HttpStatus.OK);
//        DataBufferFactory bufferFactory = response.bufferFactory();
//        byte[] bytes = JSON.toJSONString(result).getBytes();
//        DataBuffer buffer = bufferFactory.wrap(bytes);
//        return response.writeWith(Mono.just(buffer));
//    }
//}
//
