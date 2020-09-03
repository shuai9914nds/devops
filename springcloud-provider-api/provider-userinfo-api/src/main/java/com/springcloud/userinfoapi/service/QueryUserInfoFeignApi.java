package com.springcloud.userinfoapi.service;

import com.springcloud.userinfoapi.model.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author: liushuai
 * @date: 2020/8/31
 * @description：
 */
@FeignClient(value = "provider-userinfo-api")
public interface QueryUserInfoFeignApi {

    /**
     * 根据条件查询用户信息列表
     *
     * @param userInfo
     * @return
     */
    @PostMapping(value = "/api/userinfo/getUserInfoByCondition")
    List<UserInfo> getUserInfoByCondition(@RequestBody UserInfo userInfo);

    /**
     * 查询全部用户信息列表
     *
     * @return
     */
    @PostMapping(value = "/api/userinfo/getUserInfos")
    List<UserInfo> getUserInfos();
}
