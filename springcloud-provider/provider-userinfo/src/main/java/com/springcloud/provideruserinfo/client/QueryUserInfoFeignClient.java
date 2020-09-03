package com.springcloud.provideruserinfo.client;

import com.springcloud.userinfoapi.model.UserInfo;
import com.springcloud.userinfoapi.service.QueryUserInfoFeignApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: liushuai
 * @date: 2020/9/1
 * @description：
 */
@RefreshScope
@RestController
@Api(value = "API - QueryUserInfoFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class QueryUserInfoFeignClient implements QueryUserInfoFeignApi {

    /**
     * 查询用户信息列表
     * @param userInfo
     * @return
     */
    @Override
    @ApiOperation(httpMethod = "POST", value = "查询用户信息")
    public List<UserInfo> getUserInfoByCondition(UserInfo userInfo) {
        return null;
    }

    @Override
    public List<UserInfo> getUserInfos() {
        return null;
    }
}
