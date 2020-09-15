package com.springcloud.provider.userinfo.model;

public class UserRoleRel {
    private Integer id;

    private Integer uid;

    private Integer menuRoleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getMenuRoleId() {
        return menuRoleId;
    }

    public void setMenuRoleId(Integer menuRoleId) {
        this.menuRoleId = menuRoleId;
    }
}