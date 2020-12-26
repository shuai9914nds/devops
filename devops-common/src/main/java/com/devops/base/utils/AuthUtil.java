package com.devops.base.utils;

import com.devops.base.common.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author: liushuai
 * @date: 2020/11/21
 * @description：
 */
public class AuthUtil {

    private static final Logger logger = LoggerFactory.getLogger(AuthUtil.class);


    /**
     * 获取token
     *
     * @return String
     */
    public static String getToken() {
        HttpServletRequest request = HttpUtil.getRequest();
        Enumeration<String> headers = request.getHeaders(Constant.USER_TOKEN);
        if (headers == null) {
            logger.warn("token为空");
            return null;
        }
        return headers.toString();
    }
}
