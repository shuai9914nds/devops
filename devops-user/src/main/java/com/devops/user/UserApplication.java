package com.devops.user;

import com.devops.base.annotation.DevopsApplication;
import com.devops.base.annotation.MyApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@DevopsApplication
@SpringBootApplication
@ComponentScan(basePackages = {"com.devops.base.aspect", "com.devops.base.annotation", "com.devops.user.*", "com.devops.base.enums"})
@MyApplication
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }


}
