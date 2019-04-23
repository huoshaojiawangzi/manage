package com.lizc.sports;

import com.lizc.sports.common.utils.RedisUtils;
import com.lizc.sports.pc.demo.entity.User;
import com.lizc.sports.pc.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

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
        RedisUtils.setOne("one","abv");
    }
    @Test
    public void testSaveList()
    {
        List<User> users = userService.findAll();
        RedisUtils.saveList("users",users);
    }
    @Test
    public void getList()
    {
        List<User> users = RedisUtils.getList("users",User.class);
        System.out.println(users.get(0).getPhone());
    }
}
