package com.sentinel.login.loginsoa.controller;


import com.alibaba.fastjson.JSONObject;
import com.sentinel.login.loginsoa.model.UserInfo;
import com.sentinel.login.loginsoa.service.UserInfoService;
import common.ErrorCode;
import common.JsonResult;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author: liushuai
 * @date: 2020/5/16
 * @description：登录controller
 */
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserInfoService userInfoService;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * 保存用户信息
     *
     * @param param
     * @return
     * @throws Exception
     */
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public Result checkUserInfo(String param) throws Exception {
//        if (StringUtils.isEmpty(param)) {
//            return new Result(ErrorCode.PARAM_ERROR);
//        }
//        UserInfo userInfo = null;
//        try {
//            userInfo = JSONObject.parseObject(param, UserInfo.class);
//        } catch (Exception e) {
//            return new Result(ErrorCode.PARAM_ERROR);
//        }
//        if (userInfo == null || StringUtils.isEmpty(userInfo.getUname()) || StringUtils.isEmpty(userInfo.getPassword())) {
//            return new Result(ErrorCode.PARAM_ERROR);
//        }
//        Result result = null;
//        try {
//            result = userInfoService.saveUserInfo(userInfo);
//        } catch (Exception e) {
//
//        }
//        return result;
//    }


    /**
     * 登录验证
     *
     * @param param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JsonResult login(String param) throws Exception {
        if (StringUtils.isEmpty(param)) {
            return new JsonResult(ErrorCode.PARAM_ERROR);
        }
        UserInfo userInfo;
        try {
            userInfo = JSONObject.parseObject(param, UserInfo.class);
        } catch (Exception e) {
            return new JsonResult(ErrorCode.PARAM_ERROR);
        }
        if (null == userInfo || StringUtils.isEmpty(userInfo.getUname()) || StringUtils.isEmpty(userInfo.getPassword())) {
            return new JsonResult(ErrorCode.PARAM_ERROR);
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userInfo.getUname(), userInfo.getPassword());
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
           logger.error("登录失败");
            return new JsonResult(ErrorCode.PARAM_ERROR);
        }
        if (!subject.isAuthenticated()) {
            return new JsonResult(ErrorCode.PARAM_ERROR);
        }
        return new JsonResult();

    }

}
