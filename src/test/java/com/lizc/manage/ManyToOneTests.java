package com.lizc.manage;


import com.lizc.manage.sys.entity.CommonUser;
import com.lizc.manage.sys.entity.Menu;
import com.lizc.manage.sys.entity.Permission;
import com.lizc.manage.sys.sevice.CommonUserService;
import com.lizc.manage.sys.sevice.MenuService;
import com.lizc.manage.sys.sevice.PermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ManyToOneTests
{

    @Autowired
    private MenuService menuService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private CommonUserService commonUserService;

    @Test
    public void testSaveCommuser()
    {
        CommonUser commonUser = new CommonUser();
        commonUser.setName("雪见");
        commonUser.setPassword("55555");
        commonUser.setUserName("xuejian");
        commonUserService.saveAndFlush(commonUser);
    }

    @Test
    public void testUpdateCommuser()
    {
        CommonUser commonUser = new CommonUser();
        commonUser.setId("402881ef6a0a0c17016a0a0c2a110000");
        commonUser.setName("古手梨花");
        commonUser.setPassword("666666");
        commonUser.setUserName("lihua");
        commonUserService.saveAndFlush(commonUser);
    }

    @Test
    public void testGetCommuser()
    {
        CommonUser commonUser = commonUserService.get("402881ef6a0a0c17016a0a0c2a110000");
        System.out.println("*******************************************");
        System.out.println(commonUser.getUpdateDate());
    }

    @Test
    public void testSaveMenu()
    {
        Menu menu = new Menu();
        menu.setName("贷款管理查询");
        menu.setPath("archiveList");
        menuService.save(menu);
    }

    /**
     * 测试没有任何注释的menu以及permission获取
     */
    @Test
    public void testExposeGet()
    {
        List<Menu> menus = menuService.findAll();
        List<Permission> permissions = permissionService.findAll();
    }

    /**
     * 测试给permission中的list增加一个menu，保存，查看是否menu也进行了保存
     */
    @Test
    @Transactional
    public void testSave()
    {
        Permission permission = permissionService.get("402881ef69e6e0340169e6e053f30000");
        Menu menu = new Menu();
        menu.setPath("sunList");
        menu.setName("开心查询");
        permissionService.saveAndFlush(permission);
    }

}
