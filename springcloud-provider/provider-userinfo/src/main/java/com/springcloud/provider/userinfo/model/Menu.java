package com.springcloud.provider.userinfo.model;

import lombok.Data;

import java.util.Date;

@Data
public class Menu {
    private Integer menuId;

    private Integer parentId;

    private Integer moduleId;

    private String permission;

    private Integer resourceType;

    private String url;

    private Boolean isShow;

    private String icon;

    private Integer orderNum;

    private String path;

    private String component;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;
}