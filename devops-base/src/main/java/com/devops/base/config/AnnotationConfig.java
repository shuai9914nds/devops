package com.devops.base.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author: liushuai
 * @date: 2021/1/13
 * @description：
 */
@Configuration
@Slf4j
@ComponentScan("com.devops.base.annotation")
public class AnnotationConfig {
}
