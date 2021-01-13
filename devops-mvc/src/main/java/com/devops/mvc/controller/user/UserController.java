package com.devops.mvc.controller.user;

import com.devops.api.UserFeignApi;
import com.devops.api.entity.User;
import com.devops.base.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: liushuai
 * @date: 2020/11/25
 * @description：对接前端页面的controller
 */
@Slf4j
@RestController
public class UserController {

    @Resource
    private UserFeignApi userFeignApi;

    /**
     * 修改用户状态
     *
     * @param User 用户对象
     * @return Result<Void>
     */
    @PostMapping(value = "/mvc/user/state")
    public Result<Void> updateUserState(@RequestBody User User) {
        return userFeignApi.updateUserState(User.getUid(), User.getState());
    }
}
