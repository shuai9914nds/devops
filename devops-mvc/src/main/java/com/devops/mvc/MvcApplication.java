package com.devops.mvc;

import com.devops.base.annotation.DevopsApplication;
import com.devops.base.annotation.MyApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@DevopsApplication
@MyApplication
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(MvcApplication.class, args);
    }

}
