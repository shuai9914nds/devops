package com.devops.mvc.controller.login;

import com.devops.base.common.Constant;
import com.devops.base.common.Result;
import com.devops.base.common.VerifyCode;
import com.devops.base.utils.HttpUtil;
import com.devops.base.utils.SimpleCharVerifyCodeGenImpl;
import com.devops.base.utils.inter.IVerifyCodeGen;
import io.swagger.annotations.ApiOperation;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author: liushuai
 * @date: 2020/10/9
 * @description：验证码controller
 */
@RestController
public class VerifyCodeController {

    private static final Logger logger = LoggerFactory.getLogger(VerifyCodeController.class);
    @Resource
    private RedissonClient redissonClient;


    /**
     * 获取验证码
     *
     * @return Result<Void>
     */
    @ApiOperation(value = "验证码")
    @GetMapping(value = "/mvc/verify/code")
    public Result<Void> getVerifyCode() {
        HttpServletRequest request = HttpUtil.getRequest();
        HttpServletResponse response = HttpUtil.getResponse();
        System.out.println(HttpUtil.getRequest().getHeaders("User-Token"));
        IVerifyCodeGen iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();
        try {
            //设置长宽
            VerifyCode verifyCode = iVerifyCodeGen.generate(80, 28);
            String code = verifyCode.getCode();
            logger.info(code);
            //将VerifyCode绑定session
//            request.getSession().setAttribute("VerifyCode", code);
            //设置响应头
            response.setHeader("Pragma", "no-cache");
            //设置响应头
            response.setHeader("Cache-Control", "no-cache");
            //在代理服务器端防止缓冲
            response.setDateHeader("Expires", 0);
            //设置响应内容类型
            response.setContentType("image/jpeg");
            //将验证码存入redis
            RBucket<Object> bucket = redissonClient.getBucket(Constant.PRE_REDIS_VERIFY_CODE_KEY + code.toLowerCase());
            //验证码在redis中时间为5分钟
            bucket.set(code, 5, TimeUnit.MINUTES);
            response.getOutputStream().write(verifyCode.getImgBytes());
            response.getOutputStream().flush();
        } catch (IOException e) {
            logger.info("", e);
        }
        return new Result<>();
    }
}
