package com.springcloud.user.enumc;

/**
 * @author: liushuai
 * @date: 2020/10/28
 * @description：
 */
public enum UserStateEnum {

    DISABLE(0, "停用"),
    NORMAL(1, "正常"),
    LOCKED(2, "锁定");

    private final Integer code;
    private final String msg;

    UserStateEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
