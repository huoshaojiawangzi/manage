package com.lizc.sports;

import com.lizc.sports.sys.entity.CommonUser;
import com.lizc.sports.sys.sevice.CommonUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: lizc@sdhuijin.cn
 * @date: 2019-04-16 09:40
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroTest {
    @Autowired
    private CommonUserService commonUserService;

    @Test
    public void saveCommonUser()
    {
        CommonUser commonUser = new CommonUser();
        commonUser.setName("鸣人");
        commonUser.setUserName("mingren");
        commonUser.setPassword("123456");
        commonUserService.save(commonUser);
    }
}
