package com.devops.user.client;

import com.devops.base.annotation.MyLog;
import com.devops.base.common.Constant;
import com.devops.base.common.ErrorCode;
import com.devops.base.common.Result;
import com.devops.user.service.IUserInfoService;
import com.user.api.entity.User;
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
@Api(value = "API - UserClient")
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
    @MyLog(operation = "修改用户状态", type = Constant.USER_LOCK)
    @PostMapping(value = "/user/state")
    public Result<Void> updateUserState(@RequestParam("uid") Integer uid, @RequestParam("state") Integer state) {
        String token = iUserInfoService.getToken();
        if (StringUtils.isBlank(token)) {
            log.error("token为空");
            return new Result<>(ErrorCode.SYSTEM_ERROR);
        }
        User user = new User();
        user.setUid(uid);
        user.setState(state);
        iUserInfoService.updateUserInfo(user);
        return new Result<>();
    }
}
