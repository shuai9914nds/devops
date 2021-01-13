package com.devops.login;

import com.devops.base.annotation.MyApplication;
import com.devops.base.aspect.DevopsApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@DevopsApplication
@ComponentScan(basePackages = {"com.devops.base.config", "com.devops.login.*"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MyApplication
public class LoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginApplication.class, args);
    }

}
