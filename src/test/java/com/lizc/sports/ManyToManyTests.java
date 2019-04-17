package com.lizc.sports;


import com.lizc.sports.sys.entity.Menu;
import com.lizc.sports.sys.entity.Permission;
import com.lizc.sports.sys.entity.Role;
import com.lizc.sports.sys.sevice.MenuService;
import com.lizc.sports.sys.sevice.PermissionService;
import com.lizc.sports.sys.sevice.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ManyToManyTests
{

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Test
    public void testSaveRole()
    {
        Role manager = new Role();
        manager.setName("局长");
        roleService.save(manager);
    }

    @Test
    public void testSavePermission()
    {
        Permission permission = new Permission();
        permission.setName("添加用户");
        permission.setUrl("home/user/save");
        permissionService.save(permission);
    }

    @Test
    public void testRoleLinkMenu()
    {
        List<Menu> menus = menuService.findAll();
        Role role = roleService.get("402881ef6a0afc6e016a0afc83750000");
        role.setMenus(menus);
        roleService.save(role);
    }

    @Test
    public void testRoleLinkPermission()
    {
        List<Permission> permissions = permissionService.findAll();
        Role role = roleService.get("402881ef6a0afc6e016a0afc83750000");
        role.setPermissions(permissions);
        roleService.save(role);
    }

    @Test
    public void testNewRoleLinkPermission()
    {
        List<Permission> permissions = permissionService.findAll();
        Role role = new Role();
        role.setName("诉讼专管员");
        role.setPermissions(permissions);
        roleService.save(role);
    }

    @Test
    public void testNewRoleLinkTwo()
    {
        List<Permission> permissions = permissionService.findAll();
        List<Menu> menus = menuService.findAll();
        Role role = new Role();
        role.setName("资产部部长");
        role.setPermissions(permissions);
        role.setMenus(menus);
        roleService.save(role);
    }

}
