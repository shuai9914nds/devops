package com.springcloud.provider.userinfo.model;

public class UserRoleRel {
    private Integer id;

    private Integer uid;

    private Integer menuRoleId;

    private Integer moduleRoleId;

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

    public Integer getModuleRoleId() {
        return moduleRoleId;
    }

    public void setModuleRoleId(Integer moduleRoleId) {
        this.moduleRoleId = moduleRoleId;
    }
}