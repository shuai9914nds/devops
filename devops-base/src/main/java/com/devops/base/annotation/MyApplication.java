package com.devops.base.annotation;

import com.devops.base.exception.MyExceptionAdvice;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: liushuai
 * @date: 2020/12/15
 * @description：全局捕获异常
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(MyExceptionAdvice.class)
public @interface MyApplication {
}
