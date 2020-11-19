package com.springcloud.login.controller;

import com.menu.api.dto.MenuDto;
import com.menu.api.query.QueryMenuFeignApi;
import com.springcloud.login.dto.LoginDto;
import com.user.api.entity.UserInfo;
import com.user.api.query.QueryUserFeignApi;
import common.Constant;
import common.ErrorCode;
import common.Result;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: liushuai
 * @date: 2020/9/24
 * @description：
 */
@RestController
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private QueryMenuFeignApi queryMenuFeignApi;
    @Resource
    private QueryUserFeignApi queryUserFeignApi;
    @Resource
    private RedissonClient redissonClient;

    @GetMapping("/getmenu")
    public Result<List<MenuDto>> getmenu() {
        return queryMenuFeignApi.selectMenuListAll();
    }

    /**
     * 登录controller
     *
     * @param loginDto
     * @return
     */
    @PostMapping("/login")
    public Result<Void> login(@RequestBody LoginDto loginDto) {
        if (ObjectUtils.isEmpty(loginDto)) {
            logger.warn("loginDto不能为空，登录失败");
            return new Result<>(ErrorCode.PARAM_ERROR);
        }
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();
        //校验验证码是否正确
        RBucket<Object> bucket = redissonClient.getBucket(Constant.PRE_REDIS_VERIFY_CODE_KEY + loginDto.getIdentifyCode());
        Object o = bucket.get();
        if (null == o) {
            return new Result<>(ErrorCode.VERIFY_CODE_ERROR);
        }
        //校验用户名否正确
        Result<UserInfo> result = queryUserFeignApi.getUserByUserName(username);
        if (!result.getSuccess()) {
            logger.error("查询接口 queryUserFeignApi.getUserByUserNameyi失败,result={}", result);
            return new Result<>(ErrorCode.SYSTEM_ERROR);
        }
        UserInfo userInfo = result.getObj();
        if (null == userInfo) {
            logger.warn("用户{}不存在", loginDto.getUsername());
            return new Result<>(ErrorCode.UNAME_OR_PASSWORD_ERROR);
        }
        //校验密码是否正确
        if (!userInfo.getPassword().equals(password)) {
            return new Result<>(ErrorCode.UNAME_OR_PASSWORD_ERROR);
        }
        //删除redis中的验证码
        bucket.delete();
        return new Result<>();
    }
}
