package com.springcloud.gateway.login.model;

import lombok.Data;

import java.util.Date;

@Data
public class RolePermission {

    /**
     * 主键Id
     */
    private Integer id;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 权限id
     */
    private Integer permissionId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 是否有效 1有效 0无效
     */
    private Boolean status;
}