package com.springcloud.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author shuai
 * @since 2020-10-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="UserInfo对象", description="")
public class UserInfo extends Model {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "登录密码")
    private String password;

    @ApiModelProperty(value = "用户真实姓名")
    private String name;

    @ApiModelProperty(value = "用户身份证号")
    private String idCardNum;

    @ApiModelProperty(value = "用户状态：0:正常状态,1：用户被锁定")
    private Boolean state;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;
}
