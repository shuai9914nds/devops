package com.springcloud.gateway.login.service.impl;

import com.springcloud.gateway.login.mapper.UserInfoMapper;
import com.springcloud.gateway.login.model.UserInfo;
import com.springcloud.gateway.login.model.UserInfoExample;
import com.springcloud.gateway.login.service.LoginService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: liushuai
 * @date: 2020/5/25
 * @description：登录service具体逻辑
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    /**
     * 根据条件查询用户信息列表
     *
     * @param userInfo
     * @return
     */
    @Override
    public List<UserInfo> getUserInfoByCondition(UserInfo userInfo) {
        UserInfoExample userInfoExample = getUserInfoExample(userInfo);
        return userInfoMapper.selectByExample(userInfoExample);
    }

    /**
     * 查询全部用户信息
     *
     * @return
     */
    @Override
    public List<UserInfo> getUserInfos() {
        return userInfoMapper.selectByExample(null);
    }

    /**
     * 根据userInfo获取userInfoExample
     *
     * @param userInfo
     * @return
     */
    private UserInfoExample getUserInfoExample(UserInfo userInfo) {
        UserInfoExample userInfoExample = new UserInfoExample();
        UserInfoExample.Criteria criteria = userInfoExample.createCriteria();
        if (null == userInfo) {
            return userInfoExample;
        }
        if (StringUtils.isNotEmpty(userInfo.getUname())) {
            criteria.andUnameEqualTo(userInfo.getUname());
        }
        if (StringUtils.isNotEmpty(userInfo.getPassword())) {
            criteria.andPasswordEqualTo(userInfo.getPassword());
        }
        if (null != userInfo.getId()) {
            criteria.andIdEqualTo(userInfo.getId());
        }
        if (null != userInfo.getPhone()) {
            criteria.andPhoneEqualTo(userInfo.getPhone());
        }
        if (null != userInfo.getStatus()) {
            criteria.andStatusEqualTo(userInfo.getStatus());
        } else {
            criteria.andStatusEqualTo(true);
        }
        return userInfoExample;
    }
}
