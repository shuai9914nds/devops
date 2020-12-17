package com.springcloud.user.client;

import com.springcloud.user.entity.UserInfo;
import com.springcloud.user.service.IUserInfoService;
import common.ErrorCode;
import common.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: liushuai
 * @date: 2020/10/3
 * @description：
 */
@RestController
@Api(value = "API - QueryUserClient")
@Slf4j
public class UserClient {
    @Resource
    private IUserInfoService iUserInfoService;


    /**
     * 修改用户状态
     *
     * @param uid   被修改的用户Id
     * @param state 修改成state状态
     * @return Result<Void>
     */
    @PostMapping(value = "/user/state")
    public Result<Void> updateUserState(@RequestParam("uid") Integer uid, @RequestParam("state") Integer state) {
        String token = iUserInfoService.getToken();
        if (StringUtils.isBlank(token)) {
            log.error("token为空");
            return new Result<>(ErrorCode.SYSTEM_ERROR);
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUid(uid);
        userInfo.setState(state);
        iUserInfoService.updateUserInfo(userInfo);
        return new Result<>();
    }
}
