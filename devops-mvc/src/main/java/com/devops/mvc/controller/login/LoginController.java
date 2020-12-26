package com.devops.mvc.controller.login;

import com.devops.base.common.ErrorCode;
import com.devops.base.common.Result;
import com.login.api.LoginApi;
import com.login.api.dto.LoginDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author: liushuai
 * @date: 2020/11/25
 * @description：对接前端页面的controller
 */
@RestController
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private LoginApi loginApi;

    /**
     * 登录controller
     *
     * @param loginDto
     * @returnzz
     */
    @PostMapping(value = "/mvc/login")
    public Result<Map<String, Object>> login(@RequestBody LoginDto loginDto) {
        if (ObjectUtils.isEmpty(loginDto)) {
            logger.warn("loginDto不能为空，登录失败");
            return new Result<>(ErrorCode.PARAM_ERROR);
        }
        return loginApi.login(loginDto);
    }

}
