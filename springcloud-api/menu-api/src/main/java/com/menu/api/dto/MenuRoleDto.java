package com.menu.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: liushuai
 * @date: 2020/9/19
 * @description：
 */
@Data
public class MenuRoleDto implements Serializable {

    private static final long serialVersionUID = -7225810957379349380L;


    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 菜单id列表
     */
    private List<Integer> menuIds;
}
