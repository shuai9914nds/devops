package com.springcloud.provider.userinfo.service;

import com.springcloud.provider.userinfo.mapper.UserInfoMapper;
import com.springcloud.provider.userinfo.model.UserInfo;
import com.springcloud.userinfoapi.dto.UserInfoDto;
import common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.BeanConverter;

import java.util.List;

/**
 * @author: liushuai
 * @date: 2020/9/9
 * @description：
 */
@Service
public class UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    public Result<List<UserInfoDto>> getUserInfos() {
        List<UserInfo> userInfos = userInfoMapper.selectByExample(null);
        return new Result<>(BeanConverter.convertList(userInfos, UserInfoDto.class));
    }
}
