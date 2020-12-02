package com.springcloud.menu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients({"com.menu.api.query", "com.menu.api", "com.role.api.query", "com.role.api"})
@MapperScan(basePackages = {"com.springcloud.menu.mapper"})
@SpringBootApplication
public class MenuApplication {

    public static void main(String[] args) {
        SpringApplication.run(MenuApplication.class, args);
    }


}
