package com.springcloud.provider.userinfo.model;

import lombok.Data;

import java.util.Date;

@Data
public class MenuRole {
    private Integer moduleRoleId;

    private Integer moduleId;

    private Boolean isAvailable;

    private String menuRoleIdentify;

    private String menuRoleName;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;
}