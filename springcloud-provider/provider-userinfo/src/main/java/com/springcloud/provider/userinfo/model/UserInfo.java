package com.springcloud.provider.userinfo.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfo {
    private Integer uid;

    private String username;

    private String password;

    private String name;

    private String idCardNum;

    private Boolean state;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

}