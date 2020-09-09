package com.springcloud.provider.userinfo.model;

import lombok.Data;

import java.util.Date;

@Data
public class Module {
    private Integer moduleId;

    private String moduleName;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

}