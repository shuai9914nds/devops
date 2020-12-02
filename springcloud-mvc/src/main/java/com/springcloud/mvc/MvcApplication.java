package com.springcloud.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients({"com.login.api", "com.user.api", "com.user.api.query", "com.role.api.query", "com.menu.api.query", "com.menu.api"})
@SpringBootApplication
public class MvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(MvcApplication.class, args);
    }

}
