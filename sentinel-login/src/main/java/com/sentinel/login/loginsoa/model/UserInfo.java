package com.sentinel.login.loginsoa.model;

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
}