package com.springcloud.provider.userinfo.model;

import lombok.Data;

@Data
public class UserRoleRel {
    private Integer id;

    private Integer uid;

    private Integer menuRoleId;

    private Integer moduleRoleId;
}