package com.devops.menu;

import com.devops.base.annotation.MyApplication;
import com.devops.base.aspect.DevopsApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@DevopsApplication
@ComponentScan(basePackages = {"com.devops.base.config", "com.devops.menu.*"})
@SpringBootApplication
@MyApplication
public class MenuApplication {

    public static void main(String[] args) {
        SpringApplication.run(MenuApplication.class, args);
    }


}
