package com.devops.mvc.controller.user;

import com.devops.base.common.Result;
import com.user.api.UserFeignApi;
import com.user.api.entity.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: liushuai
 * @date: 2020/11/25
 * @description：对接前端页面的controller
 */
@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserFeignApi userFeignApi;

    /**
     * 修改用户状态
     *
     * @param userInfo 用户对象
     * @return Result<Void>
     */
    @PostMapping(value = "/mvc/user/state")
    public Result<Void> updateUserState(@RequestBody UserInfo userInfo) {
        return userFeignApi.updateUserState(userInfo.getUid(), userInfo.getState());
    }
}
