package com.devops.user.client.query;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.devops.api.dto.UserDto;
import com.devops.api.entity.User;
import com.devops.base.common.Constant;
import com.devops.base.common.ErrorCode;
import com.devops.base.common.Result;
import com.devops.base.utils.BeanConverter;
import com.devops.user.service.IUserService;
import com.devops.user.util.JwtUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author: liushuai
 * @date: 2020/10/3
 * @description：
 */
@Slf4j
@RestController
@Api(value = "API - QueryUserClient")
public class QueryUserClient {
    @Resource
    private IUserService iUserService;

    /**
     * 根据用户名查询用户信息 (包含密码，只允许内部调用)
     *
     * @param username 用户名
     * @return Result<UserInfoDto>
     */
    @GetMapping(value = "/{username}/user")
    public Result<User> getUserByUserName(@PathVariable("username") String username) {
        User user = new User();
        user.setUsername(username);
        return new Result<>(iUserService.getOneByCondition(user));
    }

    /**
     * 查询全部用户
     *
     * @return Result<List < UserInfoDto>>
     */
    @GetMapping(value = "/user/list")
    public Result<List<UserDto>> getUserAll() {
        List<User> userInfos = iUserService.getBaseMapper().selectList(null);
        return new Result<>(BeanConverter.convertList(userInfos, UserDto.class));
    }

    /**
     * 分页查询用户信息
     *
     * @param current 当前页
     * @param size    每页显示行数
     * @param name    用户名称
     * @return Result<IPage < User>>
     */
    @GetMapping(value = "/user/page")
    public Result<Page<User>> selectUserPage(@RequestParam("current") Long current, @RequestParam("size") Long size,
                                             @RequestParam(value = "name", required = false) String name, @RequestParam(value = "orderBy", required = false) String orderBy) {

        Page<User> page = new Page<>();
        page.setCurrent(current);
        page.setSize(size);
        OrderItem orderItem = new OrderItem();
        orderItem.setAsc(false);
        orderItem.setColumn(orderBy);
        page.setOrders(Collections.singletonList(orderItem));
        User user = new User();
        user.setName(name);
        Page<User> userInfoIPage = iUserService.selectUserPage(page, user);
        List<User> records = userInfoIPage.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return new Result<>(userInfoIPage);
        }
        records.forEach(i -> {
            i.setPassword("");
        });
        return new Result<>(userInfoIPage);
    }


    /**
     * 查询一个用户信息
     *
     * @param uid      用户id
     * @param userName 用户名
     * @return Result<User>
     */
    @GetMapping(value = "/user/one")
    public Result<User> selectUserOne(@RequestParam(value = "uid") Integer uid, @RequestParam(value = "userName", required = false) String userName) {
        if (null == uid && StringUtils.isEmpty(userName)) {
            return new Result<>(ErrorCode.PARAM_ERROR);
        }
        User queryUser = new User();
        queryUser.setUid(uid);
        queryUser.setUsername(userName);
        User userDB = iUserService.getOneByCondition(queryUser);
        userDB.setPassword("");
        return new Result<>(userDB);
    }

    /**
     * 创建token
     *
     * @param user 用户实体类
     * @return Result<String>
     */
    @GetMapping(value = "/create/token")
    public Result<String> createToken(@SpringQueryMap User user) {
        user.setUid(user.getUid());
        user.setName(user.getName());
        user.setUsername(user.getUsername());
        return new Result<>(JwtUtil.getToken(user));
    }

    /**
     * 获取用户信息
     *
     * @param token 用户的token
     * @return User
     */
    @GetMapping(value = "/token/user")
    public User getUserByToken(String token) {
        DecodedJWT decode = JWT.decode(token);
        Integer userId = decode.getClaim(Constant.DEVOPS_USER_ID).asInt();
        User user = new User();
        user.setUid(userId);
        return iUserService.getOneByCondition(user);
    }

    /**
     * 获取token
     *
     * @return token
     */
    public String getToken() {

        return iUserService.getToken();
    }
}
