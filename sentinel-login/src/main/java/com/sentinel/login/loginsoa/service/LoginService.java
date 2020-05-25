package com.sentinel.login.loginsoa.service;

import com.sentinel.login.loginsoa.model.UserInfo;

import java.util.List;

/**
 * @author: liushuai
 * @date: 2020/5/25
 * @description：登录Service
 */
public interface LoginService {

    /**
     * 根据条件查询用户信息列表
     * @param userInfo
     * @return
     */
    List<UserInfo> getUserInfoByCondition(UserInfo userInfo);

    /**
     * 查询全部用户信息列表
     * @return
     */
    List<UserInfo> getUserInfos();
}
