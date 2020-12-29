package com.devops.base.common;

/**
 * @author: liushuai
 * @date: 2020/10/27
 * @description：
 */
public class Constant {

    public static final String DEVOPS_USER_ID = "User-Id";
    public static final String DEVOPS_USER_NAME = "User-Name";

    public static final String USER_TOKEN = "User-Token";

    //菜单缓存key
    public static final String REDIS_ALL_MENUS_KEY = "redis:all:menus";

    //验证码redis的key
    public static final String PRE_REDIS_VERIFY_CODE_KEY = "redis:verify:code:";
    //token的key
    public static final String PRE_REDIS_USER_TOKEN = "redis:user:token:";
    //request的key
    public static final String PRE_REDIS_REQUEST_KEY = "pre:redis_request_key:";


    public static final int LOG_LOGIN = 101;
    public static final int USER_LOCK = 201;
    public static final int USER_DISABLE = 202;
    public static final int USER_NORMAL = 203;
    public static final int ADD_ROLE = 301;
    public static final int UPDATE_ROLE = 302;
    public static final int DELETE_ROLE = 303;
    public static final int ADD_USER_ROLE = 401;
    public static final int DELETE_USER_ROLE = 402;

}
