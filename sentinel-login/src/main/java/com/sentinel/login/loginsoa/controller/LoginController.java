package com.sentinel.login.loginsoa.controller;


import common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


/**
 * @author: liushuai
 * @date: 2020/5/16
 * @description：登录controller
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping(value = "/save")
    public Result saveUserInfo(String param) throws Exception {
        System.out.println(param);
        return null;
    }

}
