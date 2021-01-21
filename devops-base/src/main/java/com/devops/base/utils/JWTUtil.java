package com.devops.base.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.devops.base.common.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

/**
 * @author: liushuai
 * @date: 2021/1/14
 * @description：
 */
@Slf4j
public class JWTUtil {

    public static String getUserName(String token) {
        DecodedJWT decode = JWT.decode(token);
        return decode.getClaim(Constant.DEVOPS_USERNAME).asString();
    }

    public static Integer getUid(String token) {
        DecodedJWT decode = JWT.decode(token);
        return decode.getClaim(Constant.DEVOPS_USER_ID).asInt();
    }

    public static String getName(String token) {
        DecodedJWT decode = JWT.decode(token);
        return decode.getClaim(Constant.DEVOPS_NAME).asString();
    }

    public static String getIdCard(String token) {
        DecodedJWT decode = JWT.decode(token);
        return decode.getClaim(Constant.ID_CARD).asString();
    }

    public static String getToken() {
        String token = "";
        try {
            token = HttpUtil.getRequest().getHeader(Constant.USER_TOKEN);
        } catch (Exception e) {
            log.error("token为空!");
        }
        return (StringUtils.isBlank(token) || token.equals("null")) ? "" : token;
    }
}
