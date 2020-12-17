package com.test.anno.annotation;

import com.test.anno.aspect.LogType;

import java.lang.annotation.*;

/**
 * 系统日志记录
 *
 * @author cjg
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ServiceLog {
    /**
     * 操作类型,新增用户?删除用户 ?调用xx服务?使用接口?...
     *
     * @return
     */
    String operation();

    /**
     * 日志级别
     *
     * @return
     */
    LogType level() default LogType.INFO;

}