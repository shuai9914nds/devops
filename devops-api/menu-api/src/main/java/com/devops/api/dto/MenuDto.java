package com.devops.api.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
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

    private static final long serialVersionUID = 3245406510422911503L;
    @ApiModelProperty(value = "菜单id")
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Integer menuId;

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "父编号,本权限可能是该父编号权限的子权限")
    private Integer parentId;

    @ApiModelProperty(value = "权限字符串,menu例子：/user/info")
    private String permission;

    @ApiModelProperty(value = "资源类型，101菜单，201按钮，301请求地址")
    private Integer resourceType;

    @ApiModelProperty(value = "请求类型，例如GET,POST")
    private String requestType;

    @ApiModelProperty(value = "是否展示，0隐藏，1展示")
    private Boolean isShow;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "菜单顺序")
    private Integer orderNum;

    @ApiModelProperty(value = "组件路径")
    private String component;

    // 子菜单
    private List<MenuDto> children;
}
