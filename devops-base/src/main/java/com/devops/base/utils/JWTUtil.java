package com.devops.base.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.devops.base.common.Constant;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: liushuai
 * @date: 2021/1/14
 * @description：
 */
@Slf4j
public class JWTUtil {

    public static String getUserName(String token) {
        DecodedJWT decode = JWT.decode(token);
        return decode.getClaim(Constant.DEVOPS_USERNAME).toString();
    }

    public static Integer getUid(String token) {
        DecodedJWT decode = JWT.decode(token);
        return decode.getClaim(Constant.DEVOPS_USER_ID).asInt();
    }
}
