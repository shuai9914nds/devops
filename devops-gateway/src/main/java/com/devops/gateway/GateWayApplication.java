package com.devops.gateway;

import com.devops.gateway.filter.AuthFilter;
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

    @Bean
    public RouteLocator mvcRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                //basic proxy
                .route("mvc", r -> r.path("/mvc/**")
                        .uri("lb://mvc")
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
