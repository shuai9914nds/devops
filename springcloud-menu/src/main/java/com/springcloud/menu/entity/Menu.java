package com.springcloud.menu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author shuai
 * @since 2020-11-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Menu对象", description="")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单id")
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Integer menuId;

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

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "修改人")
    private String updateBy;


}
