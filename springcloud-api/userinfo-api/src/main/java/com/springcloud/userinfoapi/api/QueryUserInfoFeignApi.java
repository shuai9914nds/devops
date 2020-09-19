package com.springcloud.userinfoapi.api;

import com.springcloud.userinfoapi.dto.UserInfoDto;
import common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author: liushuai
 * @date: 2020/8/31
 * @description：
 */
@Api(value = "API - QueryUserInfoFeignApi", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@FeignClient(name = "userinfo-server", path = "/product")
public interface QueryUserInfoFeignApi {

    /**
     * 查询全部用户信息列表
     *
     * @return
     */
    @ApiOperation(httpMethod = "POST", value = "查询全部用户信息")
    @RequestMapping(value = "/getUserInfos", method = RequestMethod.POST)
    Result<List<UserInfoDto>> getUserInfos();
}
