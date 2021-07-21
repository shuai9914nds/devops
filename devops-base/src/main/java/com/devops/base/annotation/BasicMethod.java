package com.devops.base.annotation;

import java.lang.annotation.*;

/**
 * @author: liushuai
 * @date: 2021/7/13
 * @description：基本方法功能注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BasicMethod {

    String value() default "";

    String operation() default "";

    /**
     * 1、打印info级别入参 2、打印info级别方法执行时间 3、打印info级别返回结果
     */
    int[] type() default {0};
}
