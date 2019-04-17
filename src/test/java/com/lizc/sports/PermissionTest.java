package com.lizc.sports;


import com.lizc.sports.sys.entity.Permission;
import com.lizc.sports.sys.sevice.PermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * @author: lizc@sdhuijin.cn
 * @date: 2019-04-16 11:35
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class PermissionTest
{
    @Autowired
    private PermissionService permissionService;

    @Test
    public void getRoots()
    {
        List<Permission> roots = permissionService.findRoots();
        System.out.println(roots);
    }
}
