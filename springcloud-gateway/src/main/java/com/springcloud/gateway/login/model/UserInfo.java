package com.springcloud.gateway.login.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfo {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String uname;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 角色ID  101普通用户 201管理员 301超级管理员
     */
    private Integer roleId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 是否有效 1有效 0无效
     */
    private Boolean status;
}