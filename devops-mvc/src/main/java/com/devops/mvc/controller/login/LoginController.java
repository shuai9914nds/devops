package com.devops.mvc.controller.login;

import com.devops.api.LoginApi;
import com.devops.api.dto.LoginDto;
import com.devops.base.annotation.MyLog;
import com.devops.base.common.Constant;
import com.devops.base.common.ErrorCode;
import com.devops.base.common.Result;
import com.devops.base.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author: liushuai
 * @date: 2020/11/25
 * @description：对接前端页面的controller
 */
@Slf4j
@RestController
public class LoginController {


    @Resource
    private LoginApi loginApi;
    @Resource
    private RedissonClient redissonClient;

    /**
     * 登录controller
     *
     * @param loginDto
     * @returnzz
     */
    @MyLog(operation = "用户登录", type = Constant.LOG_LOGIN)
    @PostMapping(value = "/mvc/login")
    public Result<Map<String, Object>> login(@RequestBody LoginDto loginDto) {
        if (ObjectUtils.isEmpty(loginDto)) {
            log.warn("loginDto不能为空，登录失败");
            return new Result<>(ErrorCode.PARAM_ERROR);
        }
        return loginApi.login(loginDto);
    }


    /**
     * 删除token
     *
     * @return Void
     */
    @MyLog(operation = "用户登出", type = Constant.LOG_LOGIN_OUT)
    @GetMapping(value = "/mvc/login/out")
    public Result<Void> loginOut() {
        String token = JWTUtil.getToken();
        if (StringUtils.isEmpty(token)) {
            log.error("用户登出，token为空！");
            return new Result<>();
        }
        Integer uid = JWTUtil.getUid(JWTUtil.getToken());
        RBucket<Object> tokenBucket = redissonClient.getBucket(Constant.PRE_REDIS_USER_TOKEN + uid);
        tokenBucket.delete();
        return new Result<>();
    }

}
