package com.user.api;

import com.devops.base.common.Result;
import io.swagger.annotations.Api;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: liushuai
 * @date: 2020/8/31
 * @description：
 */
@Api(value = "API - UserInfoFeignApi", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@FeignClient(name = "user", path = "user", contextId = "user")
public interface UserFeignApi {


    /**
     * 修改用户状态
     *
     * @param uid   被修改的用户Id
     * @param state 修改成state状态
     * @return Result<Void>
     */
    @PostMapping(value = "/user/state")
    Result<Void> updateUserState(@RequestParam("uid") Integer uid, @RequestParam("state") Integer state);
}
