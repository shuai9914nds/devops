package com.springcloud.provider.userinfo.client;


import com.springcloud.provider.userinfo.service.UserInfoService;
import com.springcloud.userinfoapi.api.QueryUserInfoFeignApi;
import com.springcloud.userinfoapi.dto.UserInfoDto;
import common.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: liushuai
 * @date: 2020/9/1
 * @description：
 */
@RestController
@Api(value = "API - QueryUserInfoFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class QueryUserInfoFeignClient implements QueryUserInfoFeignApi {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 查询全部用户信息
     *
     * @return
     */
    @Override
    @RequestMapping(value = "/userinfo/getUserInfos", method = RequestMethod.POST)
    public Result<List<UserInfoDto>> getUserInfos() {
        return userInfoService.getUserInfos();
    }
}