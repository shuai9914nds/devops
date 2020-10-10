package com.springcloud.login.controller;

import common.VerifyCode;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.SimpleCharVerifyCodeGenImpl;
import utils.inter.IVerifyCodeGen;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: liushuai
 * @date: 2020/10/9
 * @description：
 */
@RestController
public class VerifyCodeController {

    private static final Logger logger = LoggerFactory.getLogger(VerifyCodeController.class);


    @ApiOperation(value = "验证码")
    @GetMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
        IVerifyCodeGen iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();
        try {
            //设置长宽
            VerifyCode verifyCode = iVerifyCodeGen.generate(80, 28);
            String code = verifyCode.getCode();
            logger.info(code);
            //将VerifyCode绑定session
            request.getSession().setAttribute("VerifyCode", code);
            //设置响应头
            response.setHeader("Pragma", "no-cache");
            //设置响应头
            response.setHeader("Cache-Control", "no-cache");
            //在代理服务器端防止缓冲
            response.setDateHeader("Expires", 0);
            //设置响应内容类型
            response.setContentType("image/jpeg");
            response.getOutputStream().write(verifyCode.getImgBytes());
            response.getOutputStream().flush();
        } catch (IOException e) {
            logger.info("", e);
        }
    }
}
