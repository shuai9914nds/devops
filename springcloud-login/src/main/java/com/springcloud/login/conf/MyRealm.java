package com.springcloud.login.conf;

import com.userinfo.api.query.QueryUserInfoFeignApi;
import common.Result;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

/**
 * @author: liushuai
 * @date: 2020/10/1
 * @description：
 */
public class MyRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Resource
    private QueryUserInfoFeignApi queryUserInfoFeignApi;

    /**
     * 认证,校验用户名、密码是否正确
     *
     * @param authcToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        //登录时传入的用户名
        String username = token.getUsername();
        //TODO 查看redis是否有数据,没有数据则查询数据库，没有数据则查询数据库
        Result<String> result = queryUserInfoFeignApi.getPasswordByUserName(username);
        if (StringUtils.isBlank(result.getObj())) {
            throw new AuthenticationException("用户不存在");
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                username, //用户名
                result.getObj(), //密码
                ByteSource.Util.bytes("123"),//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;
    }
}
