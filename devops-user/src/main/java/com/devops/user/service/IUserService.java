package com.devops.user.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.devops.api.entity.User;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author shuai
 * @since 2020-11-24
 */
public interface IUserService extends IService<User> {

    /**
     * 查询一个用户信息
     *
     * @param user
     * @return User
     */
    User getOneByCondition(User user);

    /**
     * 分页查询用户信息
     *
     * @param page
     * @param user
     * @return IPage<User>
     */
    Page<User> selectUserPage(Page<User> page, User user);

    /**
     * 获取token
     *
     * @return token
     */
    String getToken();


    /**
     * 更新用户信息
     *
     * @param user 用户信息
     */
    void updateUserInfo(User user);
}
