package com.lizc.manage;


import com.lizc.manage.sys.entity.CommonUser;
import com.lizc.manage.sys.entity.Permission;
import com.lizc.manage.sys.entity.Role;
import com.lizc.manage.sys.sevice.CommonUserService;
import com.lizc.manage.sys.sevice.PermissionService;
import com.lizc.manage.sys.sevice.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * @author: lizc
 * @date: 2019-04-18 11:47
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class TreeTest
{
    @Autowired
    private CommonUserService commonUserService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Test
    public void testTree()
    {
        CommonUser commonUser = commonUserService.get("1");
        Role role = roleService.getComplete(commonUser.getRoles().get(0).getId());
        List<Permission> permissionList = permissionService.findFilterRoots(role.getPermissions());
        System.out.println(permissionList.size());
    }
}
