package com.springcloud.userinfoapi.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserRole {
    private Integer id;

    private String roleName;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private Boolean status;
}