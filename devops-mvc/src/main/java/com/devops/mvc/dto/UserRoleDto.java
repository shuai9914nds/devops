package com.devops.mvc.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.role.api.entity.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: liushuai
 * @date: 2020/11/30
 * @description：用户角色dto
 */
@Data
public class UserRoleRelDto implements Serializable {
    private static final long serialVersionUID = 767221352292000181L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "用户真实姓名")
    private String name;

    @ApiModelProperty(value = "用户身份证号")
    private String idCardNum;

    @ApiModelProperty(value = "用户状态：0:正常状态,1：用户被锁定,2:用户被禁用")
    private Integer state;

    @ApiModelProperty(value = "角色实体类列表")
    List<Role> roleList;
}
