package com.springcloud.mvc.service.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springcloud.mvc.dto.UserRoleRelDto;
import com.user.api.entity.UserInfo;
import common.BaseQueryDto;

/**
 * @author: liushuai
 * @date: 2020/11/30
 * @description：
 */
public interface IUserRoleService {

    /**
     * 分页查询用户角色列表
     *
     * @param baseQueryDto 查询dto
     * @param userInfo     用户信息
     * @return 用户角色分页列表
     */
    Page<UserRoleRelDto> selectUserRolePage(BaseQueryDto baseQueryDto, UserInfo userInfo);

}
