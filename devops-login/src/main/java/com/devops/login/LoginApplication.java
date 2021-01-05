package com.devops.login;

import com.devops.base.annotation.MyApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients(basePackages = {"com.menu.*", "com.user.*", "com.login.*"})
@ComponentScan(basePackages = {"com.devops.base.config", "com.devops.login.*"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MyApplication
public class LoginApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(LoginApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        //LoginApplication是原来springboot的启动类
        return builder.sources(LoginApplication.class);
    }

}
