package com.springcloud.gateway.controller.login;

import com.login.api.VerifyCodeApi;
import common.Result;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: liushuai
 * @date: 2020/10/9
 * @description：验证码controller
 */
@RestController
public class VerifyCodeController {

    private static final Logger logger = LoggerFactory.getLogger(VerifyCodeController.class);
    @Resource
    private VerifyCodeApi verifyCodeApi;


    @ApiOperation(value = "验证码")
    @GetMapping("/verify/code")
    public Result<Void> getVerifyCode() {
        verifyCodeApi.getVerifyCode();
        return new Result<>();
    }
}
