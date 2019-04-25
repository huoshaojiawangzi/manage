package com.lizc.sports.sys.utils;


import com.lizc.sports.sys.entity.CommonUser;
import com.lizc.sports.sys.entity.Menu;
import com.lizc.sports.sys.entity.Permission;
import com.lizc.sports.sys.entity.Role;
import com.lizc.sports.sys.sevice.MenuService;
import com.lizc.sports.sys.sevice.PermissionService;
import com.lizc.sports.sys.sevice.RoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;


/**
 * @author: lizc@sdhuijin.cn
 * @date: 2019-04-15 16:49
 **/
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

    public static CommonUser getCurrentUser()
    {
        Subject subject = SecurityUtils.getSubject();
        // 得到的其实是认证逻辑返回的SimpleAuthenticationInfo(commonUser,password,"")commonUser)
        return (CommonUser)subject.getPrincipal();
    }

    public static Role getCurrentRole()
    {
        return userUtils.roleService.getComplete(getCurrentUser().getRoles().get(0).getId());
    }

    public static List<Permission> getPermissions()
    {
        return userUtils.permissionService.getfilterRoots(getCurrentRole().getPermissions());
    }

    public static List<Menu> getMenus()
    {
        return userUtils.menuService.getfilterRoots(getCurrentRole().getMenus());
    }
}
