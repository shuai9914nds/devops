package com.devops.base.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author: liushuai
 * @date: 2021/1/12
 * @description：
 */
@Slf4j
@Configuration
@Import({FeignConfig.class, RedissonConfig.class, AnnotationConfig.class, AspectConfig.class, EnumConfig.class})
public class DevopsConfig {
}
