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

import java.util.List;


public class SystemAuthorizingRealm extends AuthorizingRealm
{

    @Autowired
    private CommonUserService commonUserService;

    /**
     * 给授权信息增加权限
     * @param authorizationInfo 授权信息
     * @param permissions 权限集合
     */
    private void setSimpleAuthorizationInfo(SimpleAuthorizationInfo authorizationInfo, List<Permission> permissions)
    {
        for(Permission permission:permissions)
        {
            if(permission.getTag()!=null)
            {
                authorizationInfo.addStringPermission(permission.getTag());
            }
            if(permission.getChildren()!=null&&!permission.getChildren().isEmpty())
            {
                setSimpleAuthorizationInfo(authorizationInfo,permission.getChildren());
            }
        }
    }
    /*
     * 执行授权逻辑
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
    {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        setSimpleAuthorizationInfo(authorizationInfo,UserUtils.getCurrentPermissions());
        // 给当前用户赋予权限
        return authorizationInfo;
    }

    /*
     * 执行认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
        throws AuthenticationException
    {
        UsernamePasswordToken userToken = (UsernamePasswordToken)token;
        List<CommonUser> commonUserList = commonUserService.findByFiled("userName",userToken.getUsername());
        // 账号验证
        if (commonUserList.isEmpty())
        {
            // 底层抛出UnknownAccountException
            return null;
        }
        // 使密码对比password，成功返回SimpleAuthenticationInfo失败抛出IncorrectCredentialsException
        return new SimpleAuthenticationInfo(commonUserList.get(0), commonUserList.get(0).getPassword(), "");
    }

}
