package com.devops.role;

import com.devops.base.annotation.DevopsApplication;
import com.devops.base.annotation.MyApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@DevopsApplication
@ComponentScan(basePackages = {"com.devops.base.aspect", "com.devops.base.annotation", "com.devops.role.*"})
@SpringBootApplication
@MyApplication
public class RoleApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoleApplication.class, args);
    }


}
