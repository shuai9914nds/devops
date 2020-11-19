package com.springcloud.login.conf;

import com.user.api.query.QueryUserFeignApi;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;


public class CustomRealm extends AuthorizingRealm {

    @Resource
    private QueryUserFeignApi queryUserFeignApi;

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

//        //从主体获取传过来的用户
//        String username = (String) token.getPrincipal();
//
//        Result<UserInfoDto> result = queryUserFeignApi.getUserByUserName(username);
//        UserInfoDto userInfoDB = result.getObj();
//        //通过用户传过来从数据库进行密码
//        if (null == userInfoDB) {
//            return null;
//        }
//
//        //加盐
//        ByteSource salt = ByteSource.Util.bytes(username);
//        String realmName = this.getName();//获取当前自定义的realm
//
//        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userInfoDB, userInfoDB.getPassword(), salt, realmName);
//        return simpleAuthenticationInfo;
        return null;
    }

}
