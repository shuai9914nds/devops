package com.springcloud.provider.userinfo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@EnableEurekaServer
@SpringBootApplication
@MapperScan(basePackages = {"com.springcloud.provider.userinfo.mapper"})
public class ProviderUserinfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderUserinfoApplication.class, args);
    }

}
