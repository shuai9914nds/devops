package com.springcloud.provider.userinfo.model;

import lombok.Data;

import java.util.Date;

@Data
public class ModuleRole {
    private Integer moduleRoleId;

    private String moduleRoleName;

    private String menuRoleIdentify;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;
}