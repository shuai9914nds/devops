package com.devops.login.client;

import com.devops.base.common.Constant;
import com.devops.base.common.ErrorCode;
import com.devops.base.common.Result;
import com.login.api.dto.LoginDto;
import com.user.api.entity.UserInfo;
import com.user.api.query.QueryUserFeignApi;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: liushuai
 * @date: 2020/9/24
 * @description：
 */
@RestController
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private QueryUserFeignApi queryUserFeignApi;
    @Resource
    private RedissonClient redissonClient;

    /**
     * 登录controller
     *
     * @param loginDto
     * @returnzz
     */
    @PostMapping(value = "/login")
    public Result<Map<String, Object>> login(@RequestBody LoginDto loginDto) {
        if (ObjectUtils.isEmpty(loginDto)) {
            logger.warn("loginDto不能为空，登录失败");
            return new Result<>(ErrorCode.PARAM_ERROR);
        }
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();
        String identifyCode = loginDto.getIdentifyCode();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(identifyCode)) {
            return new Result<>(ErrorCode.PARAM_ERROR);
        }
        //校验验证码是否正确
        RBucket<Object> bucket = redissonClient.getBucket(Constant.PRE_REDIS_VERIFY_CODE_KEY + identifyCode.toLowerCase());
        Object o = bucket.get();
        if (null == o) {
            return new Result<>(ErrorCode.VERIFY_CODE_ERROR);
        }
        if (!o.toString().equalsIgnoreCase(identifyCode)) {
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
        //生成token
        Result<String> userResult = queryUserFeignApi.createToken(userInfo.getUid(), userInfo.getName());
        if (!result.getSuccess()) {
            return new Result<>(ErrorCode.SYSTEM_ERROR);
        }
        String token = userResult.getObj();
        RBucket<Object> tokenBucket = redissonClient.getBucket(Constant.PRE_REDIS_USER_TOKEN + userInfo.getUid());
        //将token放入re  dis，5min
        tokenBucket.set(token, 5, TimeUnit.MINUTES);

        Map<String, Object> map = new HashMap<>();
        map.put("userInfo", userInfo);
        map.put("token", token);
        //删除redis中的验证码
        bucket.delete();
        return new Result<>(map);
    }
}
