package com.lizc.manage.sys.utils;


import com.lizc.manage.sys.entity.CommonUser;
import com.lizc.manage.sys.entity.Menu;
import com.lizc.manage.sys.entity.Permission;
import com.lizc.manage.sys.entity.Role;
import com.lizc.manage.sys.sevice.MenuService;
import com.lizc.manage.sys.sevice.PermissionService;
import com.lizc.manage.sys.sevice.RoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;


/**
 * @author: lizc@sdhuijin.cn
 * @date: 2019-04-15 16:49
 **/
@Component
public class UserUtils
{

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private MenuService menuService;

    private static UserUtils userUtils;

    private UserUtils()
    {}

    @PostConstruct
    public void init()
    {
        userUtils = this;
        userUtils.roleService = this.roleService;
        userUtils.permissionService = this.permissionService;
        userUtils.menuService = this.menuService;
    }

    /**
     * 获取当前登录用户
     * 
     * @return CommonUser-登录用户
     */
    public static CommonUser getCurrentUser()
    {
        Subject subject = SecurityUtils.getSubject();
        // 得到的其实是认证逻辑返回的SimpleAuthenticationInfo(commonUser,password,"")commonUser)
        return (CommonUser)subject.getPrincipal();
    }

    /**
     * 获取当前登录用户角色
     * 
     * @return Role-用户角色
     */
    public static Role getCurrentRole()
    {
        return userUtils.roleService.getComplete(
            getCurrentUser().getRoles().get(getCurrentUser().getRoleIndex()).getId());
    }

    /**
     * 获取当前登录用户的权限集合
     * 
     * @return List-权限集合
     */
    public static List<Permission> getCurrentPermissions()
    {
        return userUtils.permissionService.findFilterRoots(getCurrentRole().getPermissions());
    }

    /**
     * 获取当前登录用户的菜单集合
     * 
     * @return Menu-菜单集合
     */
    public static List<Menu> getCurrentMenus()
    {
        return userUtils.menuService.findFilterRoots(getCurrentRole().getMenus());
    }
}
