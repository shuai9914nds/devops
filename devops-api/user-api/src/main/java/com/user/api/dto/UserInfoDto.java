package com.user.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserInfoDto implements Serializable {
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