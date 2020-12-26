package com.devops.mvc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author shuai
 * @since 2020-12-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "UserRoleRel对象", description = "")
public class AddUserRoleRelDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    private Integer uid;

    @ApiModelProperty(value = "菜单角色id")
    private Integer roleId;


}
