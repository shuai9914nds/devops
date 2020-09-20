package com.springcloud.provider.menu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
@MapperScan(basePackages = {"com.springcloud.provider.menu.mapper"})
public class ProviderMenuApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderMenuApplication.class, args);
    }

}
