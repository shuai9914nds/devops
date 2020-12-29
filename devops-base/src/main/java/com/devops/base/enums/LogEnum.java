package com.devops.base.enums;

/**
 * @author: liushuai
 * @date: 2020/12/29
 * @description：日志枚举
 */
public enum LogEnum {

    LOGIN(101, "登录"),
    USER_LOCK(201, "锁定用户"),
    USER_DISABLE(202, "禁用用户"),
    USER_NORMAL(203, "恢复正常"),
    ADD_ROLE(301, "新增角色"),
    UPDATE_ROLE(302, "更新角色"),
    DELETE_ROLE(303, "删除角色"),
    ADD_USER_ROLE(401, "新增用户角色"),
    DELETE_USER_ROLE(402, "删除用户角色");

    private final int code;
    private final String msg;

    LogEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
