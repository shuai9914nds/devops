package com.springcloud.user.client;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springcloud.user.entity.UserInfo;
import com.springcloud.user.service.IUserInfoService;
import com.userinfo.api.dto.UserInfoDto;
import com.userinfo.api.query.QueryUserInfoFeignApi;
import common.ErrorCode;
import common.Result;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import utils.BeanConverter;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author: liushuai
 * @date: 2020/10/3
 * @description：
 */
@RestController
@Api(value = "API - QueryUserClient")
public class QueryUserClient implements QueryUserInfoFeignApi {
    private static final Logger logger = LoggerFactory.getLogger(QueryUserClient.class);
    @Resource
    private IUserInfoService iUserInfoService;

    /**
     * 根据用户名查询密码
     *
     * @param userName
     * @return
     */
    @Override
    @GetMapping(value = "/getPasswordByUserName")
    public Result<String> getPasswordByUserName(@RequestParam("userName") String userName) {
        if (StringUtils.isBlank(userName)) {
            logger.warn("用户名不能为空");
            return new Result<>(ErrorCode.PARAM_ERROR);
        }
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("password").eq("username", userName);
        UserInfo userInfo = iUserInfoService.getOne(queryWrapper);
        return new Result<>(Optional.ofNullable(userInfo).map(UserInfo::getPassword).orElse(""));
    }

    /**
     * 根据用户名查询密码
     *
     * @param
     * @return
     */
    @Override
    @GetMapping(value = "/getUserAll")
    public Result<List<UserInfoDto>> getUserAll() {
        List<UserInfo> userInfos = iUserInfoService.getBaseMapper().selectList(null);
        return new Result<>(BeanConverter.convertList(userInfos, UserInfoDto.class));
    }
}
