package com.devops.base.annotation;

import java.lang.annotation.*;

/**
 * @author: liushuai
 * @date: 2020/12/16
 * @description：
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyLog {

    String value() default "";

    String operation() default "";

    /**
     * 101登录 201锁定用户 202禁用用户 203恢复正常 301新增角色 302修改角色 303删除角色 401为用户添加角色 402删除用户角色
     *
     * @return String
     */
    int type() default 0;
}
