package com.devops.user.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.devops.base.common.Constant;
import com.devops.user.entity.UserInfo;
import com.devops.user.service.IUserInfoService;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author: liushuai
 * @date: 2020/10/24
 * @description：
 */
public class JwtUtil {
    public static final String SUBJECT = "xdclass";

    public static final long EXPIRE = 1000 * 60 * 60 * 24 * 7;  //过期时间，毫秒，一周

    //秘钥
    public static final String APPSECRET = "xd666";

    @Resource
    private IUserInfoService iUserInfoService;


    /**
     * 生成jwt
     *
     * @param user UserInfo对象
     * @return token
     */
    public static String getToken(UserInfo user) {

        if (user == null || user.getUid() == null || user.getName() == null) {
            return null;
        }
        Algorithm algorithm = Algorithm.HMAC256(user.getName());
        return JWT.create()
                .withClaim(Constant.DEVOPS_USER_ID, user.getUid())
                .withClaim(Constant.DEVOPS_USER_NAME, user.getName())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE))
                .sign(algorithm);
    }


    /**
     * 校验token
     *
     * @param token token
     * @return token是否争取
     */
    public static boolean checkToken(String token, UserInfo userInfo) {
        try {
            Integer uid = userInfo.getUid();
            String name = userInfo.getName();
            Algorithm algorithm = Algorithm.HMAC256(name);
            JWTVerifier jwtVerifier = JWT.require(algorithm)
                    .withClaim(Constant.DEVOPS_USER_ID, uid)
                    .withClaim(Constant.DEVOPS_USER_NAME, name)
                    .acceptIssuedAt(0)
                    .build();
            jwtVerifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
