package com.springcloud.user;

import annotation.MyApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients("com.user.api.query")
@MapperScan(basePackages = {"com.springcloud.user.mapper"})
@SpringBootApplication
@ComponentScan(basePackages = {"aspect", "com.springcloud.user.*"})
@MyApplication
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
