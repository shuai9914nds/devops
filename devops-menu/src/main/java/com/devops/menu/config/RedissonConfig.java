package com.devops.menu.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: liushuai
 * @date: 2020/11/14
 * @description：
 */
@Configuration
public class RedissonConfig {
    @Bean
    public RedissonClient getRedisson(){
        Config config = new Config();
        //单机模式  依次设置redis地址和密码
        config.useSingleServer().
                setAddress("redis://172.16.24.3:6379");
        return Redisson.create(config);
    }
}
