package com.springcloud.user.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.springcloud.user.entity.UserInfo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author shuai
 * @since 2020-11-24
 */
public interface IUserInfoService extends IService<UserInfo> {

    /**
     * 查询一个用户信息
     *
     * @param userInfo
     * @return UserInfo
     */
    UserInfo getOneByCondition(UserInfo userInfo);

    /**
     * 分页查询用户信息
     *
     * @param page
     * @param userInfo
     * @return IPage<UserInfo>
     */
    Page<UserInfo> selectUserPage(Page<UserInfo> page, UserInfo userInfo);

    /**
     * 获取token
     *
     * @return token
     */
    String getToken();


    /**
     * 更新用户信息
     *
     * @param userInfo 用户信息
     */
    void updateUserInfo(UserInfo userInfo);
}
