package com.lizc.manage;


import com.lizc.manage.common.utils.RedisUtils;
import com.lizc.manage.pc.user.entity.User;
import com.lizc.manage.pc.user.service.UserService;
import com.lizc.manage.sys.entity.Permission;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;


/**
 * @author: lizc@sdhuijin.cn
 * @date: 2019-04-23 16:53
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest
{
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private UserService userService;

    @Test
    public void saveOne()
    {
        RedisUtils.setOne("test:jedisTest1", "15555", 1);
    }

    @Test
    public void delOne()
    {
        RedisUtils.del("users2");
    }

    @Test
    public void getOne()
    {
        String one = RedisUtils.getOne("jedisTest");
        if (one == null)
        {
            System.out.println("为空");
        }
        else
        {
            System.out.println("值=" + one);
        }
    }

    @Test
    public void testSaveList()
    {
        List<User> users = userService.findAll();
        RedisUtils.setList("users2", users);
    }

    @Test
    public void getList()
    {
        List<User> users = RedisUtils.getList("users2", User.class);
        if (users == null)
        {
            System.out.println("列表为空");
        }
        else
        {
            System.out.println(users.get(0).getPhone());
        }
    }

    @Test
    public void testMysql()
    {
        LocalTime loaclTimeBegan = LocalTime.now();
        for (int i = 0; i < 5000; i++ )
        {
            userService.findAll();
        }
        LocalTime loaclTimeEnd = LocalTime.now();
        Duration d = Duration.between(loaclTimeBegan, loaclTimeEnd);
        System.out.println(d.toMillis());
    }

    @Test
    public void testRedis()
    {
        LocalTime loaclTimeBegan = LocalTime.now();
        for (int i = 0; i < 5000; i++ )
        {
            RedisUtils.getList("users2", User.class);
        }
        LocalTime loaclTimeEnd = LocalTime.now();
        Duration d = Duration.between(loaclTimeBegan, loaclTimeEnd);
        System.out.println(d.toMillis());
    }

    @Test
    public void getRedisList()
    {
        List pers = RedisUtils.getList("allEnableList:" + Permission.class.toString(),
            Permission.class);
        System.out.println(pers);
    }
}
