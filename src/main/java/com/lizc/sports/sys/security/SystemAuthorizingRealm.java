package com.lizc.sports.sys.security;


import com.lizc.sports.sys.entity.CommonUser;
import com.lizc.sports.sys.entity.Permission;
import com.lizc.sports.sys.sevice.CommonUserService;
import com.lizc.sports.sys.utils.UserUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


public class SystemAuthorizingRealm extends AuthorizingRealm
{

    @Autowired
    private CommonUserService commonUserService;

    /* 执行授权逻辑
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
    {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        CommonUser currUser = UserUtils.getCurrentUser();
        for(Permission permission:UserUtils.getCurrentRole().getPermissions())
        {
            authorizationInfo.addStringPermission(permission.getName());
        }
        //给当前用户赋予权限
        return authorizationInfo;
    }

    /* 执行认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
        throws AuthenticationException
    {
        UsernamePasswordToken userToken = (UsernamePasswordToken)token;
        CommonUser commonUser = commonUserService.findByUserName(userToken.getUsername());
        //账号验证
        if(commonUser == null)
        {
          //底层抛出UnknownAccountException
            return null;
        }
        //使密码对比password，成功返回SimpleAuthenticationInfo失败抛出IncorrectCredentialsException
        return new SimpleAuthenticationInfo(commonUser,commonUser.getPassword(),"");
    }

}
