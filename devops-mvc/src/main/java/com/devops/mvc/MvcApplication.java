package com.devops.mvc;

import com.devops.base.annotation.MyApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients({"com.login.*", "com.user.*", "com.menu.*", "com.role.*"})
@ComponentScan(basePackages = {"com.devops.base.config", "com.devops.mvc.*"})
@MyApplication
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(MvcApplication.class, args);
    }

}
