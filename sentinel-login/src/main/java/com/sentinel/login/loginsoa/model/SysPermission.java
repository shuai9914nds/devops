package com.sentinel.login.loginsoa.model;

import lombok.Data;

@Data
public class SysPermission {
    private Integer id;

    private Integer parentId;

    private String parentIds;

    private String permission;

    private String resourceType;

    private String url;

    private String name;

    private Boolean status;
}