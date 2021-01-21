package com.devops.mvc.controller.user.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.devops.api.entity.User;
import com.devops.api.query.QueryUserFeignApi;
import com.devops.base.common.ErrorCode;
import com.devops.base.common.Result;
import com.devops.base.utils.JWTUtil;
import org.apache.commons.lang.StringUtils;
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

    @Resource
    private QueryUserFeignApi queryUserFeignApi;

    /**
     * 分页查询用户信息
     *
     * @param current 当前页
     * @param size    每页显示行数
     * @param name    用户名称
     * @return Result<IPage < User>>
     */
    @GetMapping(value = "/mvc/user/page")
    public Result<Page<User>> selectUserPage(@RequestParam("current") Long current, @RequestParam("size") Long size,
                                             @RequestParam(value = "name", required = false) String name, @RequestParam(value = "orderBy", required = false) String orderBy) {
        return queryUserFeignApi.selectUserPage(current, size, name, orderBy);
    }

    /**
     * 查询当前用户信息
     *
     * @return
     */
    @GetMapping(value = "/mvc/user/one")
    public Result<User> analyToken() {
//        String token = request.getHeader(Constant.USER_TOKEN);
        String token = JWTUtil.getToken();
        if (StringUtils.isBlank(token)) {
            return new Result<>(ErrorCode.TOKEN_IS_NULL);
        }
        Integer uid = JWTUtil.getUid(token);
        String userName = JWTUtil.getUserName(token);
        String name = JWTUtil.getName(token);
        String idCard = JWTUtil.getIdCard(token);
        User user = User.builder()
                .uid(uid)
                .name(name)
                .username(userName)
                .idCardNum(idCard)
                .build();
        return new Result<>(user);
    }

}
