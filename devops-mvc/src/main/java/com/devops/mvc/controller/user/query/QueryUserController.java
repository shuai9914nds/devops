package com.devops.mvc.controller.user.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.devops.base.common.Result;
import com.user.api.entity.UserInfo;
import com.user.api.query.QueryUserFeignApi;
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
public class QueryUserController {
    private static final Logger logger = LoggerFactory.getLogger(QueryUserController.class);

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
                                                 @RequestParam(value = "name", required = false) String name, @RequestParam(value = "orderBy", required = false) String orderBy) {
        return queryUserFeignApi.selectUserPage(current, size, name, orderBy);
    }
}
