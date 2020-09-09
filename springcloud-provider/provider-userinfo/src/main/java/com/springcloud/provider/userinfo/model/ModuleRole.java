package com.springcloud.provider.userinfo.model;

import java.util.Date;

public class ModuleRole {
    private Integer moduleRoleId;

    private String moduleRoleName;

    private String menuRoleIdentify;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    public Integer getModuleRoleId() {
        return moduleRoleId;
    }

    public void setModuleRoleId(Integer moduleRoleId) {
        this.moduleRoleId = moduleRoleId;
    }

    public String getModuleRoleName() {
        return moduleRoleName;
    }

    public void setModuleRoleName(String moduleRoleName) {
        this.moduleRoleName = moduleRoleName;
    }

    public String getMenuRoleIdentify() {
        return menuRoleIdentify;
    }

    public void setMenuRoleIdentify(String menuRoleIdentify) {
        this.menuRoleIdentify = menuRoleIdentify;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
}