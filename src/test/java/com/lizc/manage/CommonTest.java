package com.lizc.manage;


import com.lizc.manage.common.utils.MD5;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author: lizc
 * @date: 2019-04-18 11:47
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class CommonTest
{
    @Test
    public void testTree()
    {
        System.out.println(MD5.md5("1234561"));
    }
}
