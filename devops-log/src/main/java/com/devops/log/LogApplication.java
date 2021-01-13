package com.devops.log;

import com.devops.base.annotation.MyApplication;
import com.devops.base.aspect.DevopsApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@DevopsApplication
@MyApplication
@SpringBootApplication
public class LogApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogApplication.class, args);
    }
}
