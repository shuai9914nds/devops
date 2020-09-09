package com.springcloud.consumer.userinfo.web;


import com.springcloud.consumer.userinfo.client.UserInfoClient;
import com.springcloud.userinfoapi.dto.UserInfoDto;
import common.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author: liushuai
 * @date: 2020/9/9
 * @description：
 */
@RestController
@Api(value = "API - LoginFeign", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LoginFeignClient {

    @Autowired
    private UserInfoClient userInfoClient;

    @PostMapping("/getUserInfos")
    public Result<List<UserInfoDto>> getUserInfos() {
        return userInfoClient.getUserInfos();
    }
}
