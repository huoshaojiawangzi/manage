package com.lizc.sports.sys.security;


import com.lizc.sports.pc.demo.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;


public class UserRealm extends AuthorizingRealm
{

    /* 执行授权逻辑
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
    {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        //得到的其实是认证逻辑返回的SimpleAuthenticationInfo(user,password,"")中的user
        User user = (User)subject.getPrincipal();
        //给当前用户赋予权限
        authorizationInfo.addStringPermission(user.getAuthority());
        return authorizationInfo;
    }

    /* 执行认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
        throws AuthenticationException
    {
        String userName = "admin";
        String password = "123456";
        UsernamePasswordToken userToken = (UsernamePasswordToken)token;
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setAuthority("user:add");
        //账号验证
        if(!userName.equals(userToken.getUsername()))
        {
          //底层抛出UnknownAccountException
            return null;
        }
        //使密码对比password，成功返回SimpleAuthenticationInfo失败抛出IncorrectCredentialsException
        return new SimpleAuthenticationInfo(user,password,"");
    }

}
