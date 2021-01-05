package com.devops.role;

import com.devops.base.annotation.MyApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients({"com.role.*"})
@ComponentScan(basePackages = {"com.devops.base.aspect", "com.devops.base.annotation", "com.devops.role.*"})

@SpringBootApplication
@MyApplication
public class RoleApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(RoleApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        //RoleApplication是原来springboot的启动类
        return builder.sources(RoleApplication.class);
    }

}
