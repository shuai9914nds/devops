package com.devops.base.aspect;

import com.devops.base.config.DevopsConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: liushuai
 * @date: 2021/1/12
 * @description：
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(DevopsConfig.class)
public @interface DevopsApplication {
}
