package com.springcloud.user.client;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springcloud.user.entity.UserInfo;
import com.springcloud.user.service.IUserInfoService;
import com.springcloud.user.util.JwtUtil;
import com.user.api.dto.UserInfoDto;
import common.Constant;
import common.Result;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
public class QueryUserClient {
    private static final Logger logger = LoggerFactory.getLogger(QueryUserClient.class);
    @Resource
    private IUserInfoService iUserInfoService;

    /**
     * 根据用户名查询用户信息 (包含密码，只允许内部调用)
     *
     * @param username 用户名
     * @return Result<UserInfoDto>
     */
    @GetMapping(value = "/{username}/user")
    public Result<UserInfo> getUserByUserName(@PathVariable("username") String username) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        return new Result<>(iUserInfoService.getOneByCondition(userInfo));
    }

    /**
     * 查询全部用户
     *
     * @param
     * @return
     */
    @GetMapping(value = "/user/list")
    public Result<List<UserInfoDto>> getUserAll() {
        List<UserInfo> userInfos = iUserInfoService.getBaseMapper().selectList(null);
        return new Result<>(BeanConverter.convertList(userInfos, UserInfoDto.class));
    }

    /**
     * 查询全部用户
     *
     * @param
     * @return
     */
    @GetMapping(value = "/user/page")
    public Result<IPage<UserInfo>> selectUserPage(@RequestParam("current") Long current, @RequestParam("size") Long size) {
        Page<UserInfo> page = new Page<>();
        page.setCurrent(current);
        page.setSize(size);
        IPage<UserInfo> userInfoIPage = iUserInfoService.selectUserPage(page, null);
        List<UserInfo> records = userInfoIPage.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return new Result<>(userInfoIPage);
        }
        records.forEach(i -> {
            i.setPassword("");
        });
        return new Result<>(userInfoIPage);
    }

    /**
     * 创建token
     *
     * @param uid  用户id
     * @param name 用户名称
     * @return Result<String>
     */
    @GetMapping(value = "/create/token")
    public Result<String> createToken(@RequestParam("uid") Integer uid, @RequestParam("name") String name) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUid(uid);
        userInfo.setName(name);
        return new Result<>(JwtUtil.getToken(userInfo));
    }

    /**
     * 获取用户信息
     *
     * @param token 用户的token
     * @return UserInfo
     */
    @GetMapping(value = "/token/user")
    public UserInfo getUserByToken(String token) {
        DecodedJWT decode = JWT.decode(token);
        Integer userId = decode.getClaim(Constant.DEVOPS_USER_ID).asInt();
        UserInfo userInfo = new UserInfo();
        userInfo.setUid(userId);
        return iUserInfoService.getOneByCondition(userInfo);
    }
}
