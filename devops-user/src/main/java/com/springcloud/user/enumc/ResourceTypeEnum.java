package com.springcloud.user.enumc;

/**
 * @author: liushuai
 * @date: 2020/10/28
 * @description：
 */
public enum ResourceTypeEnum {

    MENU(101, "菜单"),
    BUTTON(201, "按钮"),
    URI(301, "uri");

    private final Integer code;
    private final String msg;

    ResourceTypeEnum(Integer code, String msg) {
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
