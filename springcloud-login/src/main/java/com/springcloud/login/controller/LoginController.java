package com.springcloud.login.controller;

import com.springcloud.login.dto.LoginDto;
import com.menu.api.query.QueryMenuFeignApi;
import com.menu.api.dto.MenuDto;
import common.ErrorCode;
import common.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: liushuai
 * @date: 2020/9/24
 * @description：
 */
@RestController
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private QueryMenuFeignApi queryMenuFeignApi;

    @GetMapping("/getmenu")
    public Result<List<MenuDto>> getmenu(){
        return queryMenuFeignApi.selectMenuListAll();
    }

    /**
     * 登录controller
     * @param loginDto
     * @return
     */
    @PostMapping("/login")
    public Result<Void> login(@RequestBody LoginDto loginDto) {
        if (ObjectUtils.isEmpty(loginDto)) {
            logger.warn("loginDto不能为空，登录失败");
            return new Result<>(ErrorCode.PARAM_ERROR);
        }
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password,false);
        //TODO 查看redis是否有对应token
        token.setRememberMe(true);
        Subject subject = SecurityUtils.getSubject();
        try {
            //登录验证
            subject.login(token);
        } catch(AuthenticationException e){
            return new Result<>(ErrorCode.UNAME_OR_PASSWORD_ERROR);
        }
        return new Result<>();
    }
}
