package com.devops.log;

import com.devops.base.annotation.MyApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = {"com.log.*"})
@MyApplication
@SpringBootApplication
public class LogApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(LogApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        //LogApplication是原来springboot的启动类
        return builder.sources(LogApplication.class);
    }

}
