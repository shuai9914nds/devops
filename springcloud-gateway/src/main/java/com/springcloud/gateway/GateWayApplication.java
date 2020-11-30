package com.springcloud.gateway;

import com.springcloud.gateway.filter.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class GateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class, args);
    }

//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                //basic proxy
//                .route(r -> r.path("/userinfo/**")
//                        .uri("https://www.sougou.com")
//                ).build();
//    }


//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                //basic proxy
//                .route("login-server",r -> r.path("/login/**")
//                        .uri("lb://login-server")
//                ).build();
//    }
//
//    @Bean
//    public RouteLocator userRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                //basic proxy
//                .route("user-server",r -> r.path("/user/**")
//                        .uri("lb://user-server")
//                ).build();
//    }
//
//    @Bean
//    public RouteLocator menuRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                //basic proxy
//                .route("menu-server",r -> r.path("/menu/**")
//                        .uri("lb://menu-server")
//                ).build();
//    }

    @Bean
    public RouteLocator mvcRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                //basic proxy
                .route("mvc-server", r -> r.path("/mvc/**")
                        .uri("lb://mvc-server")
                ).build();
    }

//    @Bean
//    public TokenFilter tokenFilter() {
//        return new TokenFilter();
//    }
    @Bean
    public AuthFilter authFilter() {
        return new AuthFilter();
    }
}
