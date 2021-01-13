package com.devops.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: liushuai
 * @date: 2020/10/1
 * @description：登录用的dto
 */
@Data
public class LoginDto implements Serializable {
    private static final long serialVersionUID = 400580548822941754L;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "登录密码")
    private String password;

    @ApiModelProperty(value = "验证码")
    private String identifyCode;

}
