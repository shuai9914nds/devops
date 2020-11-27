package com.user.api.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.user.api.dto.UserInfoDto;
import com.user.api.entity.UserInfo;
import common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author: liushuai
 * @date: 2020/8/31
 * @description：
 */
@Api(value = "API - QueryUserInfoFeignApi", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@FeignClient(name = "user-server", path = "user", contextId = "queryUser")
public interface QueryUserFeignApi {

    /**
     * 根据用户名查询密码(包含密码，只允许内部调用)
     *
     * @return Result<UserInfoDto>
     */
    @ApiOperation(httpMethod = "GET", value = "根据用户名查询用户信息，包含密码")
    @GetMapping(value = "/{username}/user")
    Result<UserInfo> getUserByUserName(@PathVariable("username") String username);

    /**
     * 查询全部用户信息列表
     *
     * @return
     */
    @ApiOperation(httpMethod = "GET", value = "查询全部用户信息列表")
    @GetMapping(value = "/user/list")
    Result<List<UserInfoDto>> getUserAll();

    /**
     * 创建token
     *
     * @param uid  用户id
     * @param name 用户名称
     * @return Result<String>
     */
    @GetMapping(value = "/create/token")
    Result<String> createToken(@RequestParam("uid") Integer uid, @RequestParam("name") String name);

    /**
     * 分页查询用户信息
     *
     * @param current 当前页
     * @param size    每页显示行数
     * @param name    用户名称
     * @return Result<IPage < UserInfo>>
     */
    @GetMapping(value = "/user/page")
    Result<Page<UserInfo>> selectUserPage(@RequestParam("current") Long current, @RequestParam("size") Long size,
                                          @RequestParam(value = "name", required = false) String name);
}
