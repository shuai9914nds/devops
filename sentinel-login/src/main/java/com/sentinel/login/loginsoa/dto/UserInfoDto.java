package com.sentinel.login.loginsoa.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: liushuai
 * @date: 2020/5/19
 * @description：用户信息dto
 */
@Data
public class UserInfoDto implements Serializable {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String uname;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 昵称
     */
    private String nickName;

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
}
