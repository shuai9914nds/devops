package com.springcloud.mvc.controller.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.user.api.entity.UserInfo;
import com.user.api.query.QueryUserFeignApi;
import common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    private QueryUserFeignApi queryUserFeignApi;

    /**
     * 分页查询用户信息
     *
     * @param current 当前页
     * @param size    每页显示行数
     * @param name    用户名称
     * @return Result<IPage < UserInfo>>
     */
    @GetMapping(value = "/mvc/user/page")
    public Result<Page<UserInfo>> selectUserPage(@RequestParam("current") Long current, @RequestParam("size") Long size,
                                                   @RequestParam(value = "name", required = false) String name) {
        Result<Page<UserInfo>> pageResult = queryUserFeignApi.selectUserPage(current, size, name);
        System.out.println(pageResult);
        return pageResult;
    }
}