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
@ApiModel(value="MenuRole对象", description="")
public class MenuRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "menu_role_id", type = IdType.AUTO)
    private Integer menuRoleId;

    @ApiModelProperty(value = "是否可用1可用  0停用")
    private Boolean isAvailable;

    @ApiModelProperty(value = "角色标识程序中判断使用,如admin")
    private String menuRoleIdentify;

    @ApiModelProperty(value = "角色描述,UI界面显示使用")
    private String menuRoleName;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;


}
