package com.userinfo.api.query;

import com.userinfo.api.dto.UserInfoDto;
import common.Result;
import feign.Param;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author: liushuai
 * @date: 2020/8/31
 * @description：
 */
@Api(value = "API - QueryUserInfoFeignApi", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@FeignClient(name = "user-server", path = "/user")
public interface QueryUserInfoFeignApi {

    /**
     * 根据用户名查询密码
     *
     * @return
     */
    @ApiOperation(httpMethod = "GET", value = "根据用户名查询密码")
    @GetMapping(value = "/getPasswordByUserName")
    Result<String> getPasswordByUserName(@RequestParam("userName") String userName);

    /**
     * 查询全部用户信息列表
     *
     * @return
     */
    @ApiOperation(httpMethod = "GET", value = "根据用户名查询密码")
    @GetMapping(value = "/getUserAll")
    Result<List<UserInfoDto>> getUserAll();
}
