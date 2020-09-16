package com.springcloud.consumer.userinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients("com.springcloud.userinfoapi.api")
@SpringBootApplication
public class ConsumerUserinfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerUserinfoApplication.class, args);
    }


}
