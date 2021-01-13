package com.devops.menu;

import com.devops.base.annotation.DevopsApplication;
import com.devops.base.annotation.MyApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@DevopsApplication
@SpringBootApplication
@MyApplication
public class MenuApplication {

    public static void main(String[] args) {
        SpringApplication.run(MenuApplication.class, args);
    }


}
