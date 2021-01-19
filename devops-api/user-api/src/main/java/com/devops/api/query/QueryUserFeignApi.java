package com.devops.api.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.devops.api.dto.UserDto;
import com.devops.api.entity.User;
import com.devops.base.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
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
@FeignClient(name = "user", path = "user", contextId = "queryUser")
public interface QueryUserFeignApi {

    /**
     * 根据用户名查询密码(包含密码，只允许内部调用)
     *
     * @return Result<UserInfoDto>
     */
    @ApiOperation(httpMethod = "GET", value = "根据用户名查询用户信息，包含密码")
    @GetMapping(value = "/{username}/user")
    Result<User> getUserByUserName(@PathVariable("username") String username);

    /**
     * 查询全部用户信息列表
     *
     * @return
     */
    @ApiOperation(httpMethod = "GET", value = "查询全部用户信息列表")
    @GetMapping(value = "/user/list")
    Result<List<UserDto>> getUserAll();

    /**
     * 创建token
     *
     * @param user 用户实体类
     * @return Result<String>
     */
    @GetMapping(value = "/create/token")
    Result<String> createToken(@SpringQueryMap User user);

    /**
     * 分页查询用户信息
     *
     * @param current 当前页
     * @param size    每页显示行数
     * @param name    用户名称
     * @return Result<IPage < User>>
     */
    @GetMapping(value = "/user/page")
    Result<Page<User>> selectUserPage(@RequestParam("current") Long current, @RequestParam("size") Long size,
                                      @RequestParam(value = "name", required = false) String name, @RequestParam(value = "orderBy", required = false) String orderBy);

    /**
     * 查询一个用户信息
     *
     * @param uid      用户id
     * @param userName 用户名
     * @return Result<User>
     */
    @GetMapping(value = "/user/one")
    Result<User> selectUserOne(@RequestParam(value = "uid") Integer uid, @RequestParam(value = "userName", required = false) String userName);

}
