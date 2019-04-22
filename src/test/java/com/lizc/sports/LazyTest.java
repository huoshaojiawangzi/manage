package com.lizc.sports;


import com.lizc.sports.pc.demo.entity.User;
import com.lizc.sports.pc.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * @author: lizc@sdhuijin.cn
 * @date: 2019-04-18 11:47
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class LazyTest
{
    @Autowired
    private UserService userService;

    @Test
    public void findAllTest()
    {
        List<User> users = userService.findAll();
        System.out.println(users.get(0).getCommonUser().getName());
    }
}
