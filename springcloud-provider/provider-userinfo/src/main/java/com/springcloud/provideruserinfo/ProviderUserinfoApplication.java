package com.springcloud.provideruserinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@EnableEurekaServer
@SpringBootApplication
public class ProviderUserinfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderUserinfoApplication.class, args);
    }

}
