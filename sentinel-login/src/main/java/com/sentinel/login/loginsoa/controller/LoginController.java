package com.sentinel.login.loginsoa.controller;


import com.alibaba.fastjson.JSONObject;
import com.sentinel.login.loginsoa.model.UserInfo;
import com.sentinel.login.loginsoa.service.UserInfoService;
import common.ErrorCode;
import common.Result;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author: liushuai
 * @date: 2020/5/16
 * @description：登录controller
 */
@CrossOrigin(maxAge=3600)
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserInfoService userInfoService;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * 登录时核对用户信息
     *
     * @param param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result checkUserInfo(String param) throws Exception {
        if (StringUtils.isEmpty(param)) {
            return new Result(ErrorCode.PARAM_ERROR);
        }
        UserInfo userInfo = null;
        try {
            userInfo = JSONObject.parseObject(param, UserInfo.class);
        } catch (Exception e) {
            return new Result(ErrorCode.PARAM_ERROR);
        }
        if (userInfo == null || StringUtils.isEmpty(userInfo.getUname()) || StringUtils.isEmpty(userInfo.getPassword())) {
            return new Result(ErrorCode.PARAM_ERROR);
        }
        Result result = null;
        try {
            result = userInfoService.checkUserInfo(userInfo);
        } catch (Exception e) {

        }
        return result;
    }

}
