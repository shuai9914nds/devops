package com.springcloud.user.client;

import com.springcloud.user.entity.UserInfo;
import com.springcloud.user.service.IUserInfoService;

import com.user.api.dto.UserInfoDto;
import com.user.api.query.QueryUserFeignApi;
import common.Result;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import utils.BeanConverter;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: liushuai
 * @date: 2020/10/3
 * @description：
 */
@RestController
@Api(value = "API - QueryUserClient")
public class QueryUserClient implements QueryUserFeignApi {
    private static final Logger logger = LoggerFactory.getLogger(QueryUserClient.class);
    @Resource
    private IUserInfoService iUserInfoService;

    /**
     * 根据用户名查询密码
     *
     * @param username 用户名
     * @return Result<UserInfoDto
     */
    @Override
    @GetMapping(value = "/user")
    public Result<UserInfoDto> getUserByUserName(@RequestParam("username") String username) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        return new Result<>(
                BeanConverter.convert(iUserInfoService.getOneByCondition(userInfo), UserInfoDto.class));
    }

    /**
     * 查询全部用户
     *
     * @param
     * @return
     */
    @Override
    @GetMapping(value = "/users")
    public Result<List<UserInfoDto>> getUserAll() {
        List<UserInfo> userInfos = iUserInfoService.getBaseMapper().selectList(null);
        return new Result<>(BeanConverter.convertList(userInfos, UserInfoDto.class));
    }
}
