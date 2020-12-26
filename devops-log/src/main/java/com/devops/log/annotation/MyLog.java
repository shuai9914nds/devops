package com.devops.log.annotation;

import java.lang.annotation.*;

/**
 * @author: liushuai
 * @date: 2020/12/16
 * @description：
 */
@Target(ElementType.METHOD)//注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyLog {

    String value() default "";
}
