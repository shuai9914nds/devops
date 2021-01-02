package com.devops.mvc.service.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.devops.base.common.BaseQueryDto;
import com.devops.mvc.dto.UserRoleRelDto;
import com.user.api.entity.User;

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
     * @param User         用户信息
     * @return 用户角色分页列表
     */
    Page<UserRoleRelDto> selectUserRolePage(BaseQueryDto baseQueryDto, User User);

}
