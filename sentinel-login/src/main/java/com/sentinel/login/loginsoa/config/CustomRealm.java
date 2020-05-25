package com.sentinel.login.loginsoa.config;

import com.sentinel.login.loginsoa.model.UserInfo;
import com.sentinel.login.loginsoa.service.LoginService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private LoginService loginService;

    /**
     * 授权管理
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

//		//从主体获取用户
//		String username=(String)principals.getPrimaryPrincipal();
//
//		//从数据库查询用户的角色
//		String rolse=userService.queryRolse(username);
//		SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
//		simpleAuthorizationInfo.addRole(rolse);
//		return simpleAuthorizationInfo;
        return null;
    }


    /**
     * 认证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //从主体获取传过来的用户
        String username = (String) token.getPrincipal();
        UserInfo userInfo = new UserInfo();
        List<UserInfo> userInfos = loginService.getUserInfoByCondition(userInfo);

        if (CollectionUtils.isEmpty(userInfos) || StringUtils.isEmpty(userInfos.get(0).getPassword())) {
            return null;
        }

        //加盐
        ByteSource salt = ByteSource.Util.bytes(username);
        String realmName = this.getName();//获取当前自定义的realm

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, userInfos.get(0).getPassword(), salt, realmName);
        return simpleAuthenticationInfo;
    }

}
