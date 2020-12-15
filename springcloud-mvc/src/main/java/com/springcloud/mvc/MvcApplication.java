package com.springcloud.mvc;

import annotation.MyApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients({"com.login.api", "com.user.api", "com.user.api.query", "com.menu.api.query", "com.menu.api", "com.role.api", "com.role.api.query"})
@SpringBootApplication
@MyApplication
public class MvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(MvcApplication.class, args);
    }

}
