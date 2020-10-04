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
public class MenuDto implements Serializable {

    private Integer menuId;

    private Integer parentId;

    private String permission;

    private Integer resourceType;

    private String url;

    private Boolean isShow;

    private String icon;

    private Integer orderNum;

    private String path;

    private String component;

    // 子菜单
    private List<MenuDto> childMenus;
}
