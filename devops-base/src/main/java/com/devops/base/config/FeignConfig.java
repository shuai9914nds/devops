package com.devops.base.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;

/**
 * @author: liushuai
 * @date: 2021/1/12
 * @description：
 */
@Slf4j
@Configuration
//就是说只有在classpath下能找到FeignClient类才会构建这个bea
@ConditionalOnClass(FeignClient.class)
@EnableFeignClients(basePackages = {"com.devops.api"})
public class FeignConfig {

}
