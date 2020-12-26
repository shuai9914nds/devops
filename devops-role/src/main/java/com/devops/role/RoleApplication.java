package com.devops.role;

import com.devops.base.annotation.MyApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients({"com.role.*"})
@SpringBootApplication
@MyApplication
public class RoleApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoleApplication.class, args);
    }

}
