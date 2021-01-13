package com.devops.role.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.devops.api.entity.Role;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shuai
 * @since 2020-11-29
 */
public interface IRoleService extends IService<Role> {


    /**
     * 查询一个角色信息
     *
     * @param role 查询条件
     * @return role
     */
    Role getOneByCondition(Role role);

    /**
     * 查询角色信息列表
     *
     * @param role
     * @return List<Role>
     */
    List<Role> selectUserList(Role role);

    /**
     * 分页查询角色信息
     *
     * @param page 分页信息
     * @param role 查询条件
     * @return IPage<Role>
     */
    Page<Role> selectUserPage(Page<Role> page, Role role);
}
