package com.lizc.manage;


import com.lizc.manage.pc.user.entity.Office;
import com.lizc.manage.pc.user.entity.User;
import com.lizc.manage.pc.user.service.OfficeService;
import com.lizc.manage.pc.user.service.UserService;
import com.lizc.manage.sys.entity.CommonUser;
import com.lizc.manage.sys.sevice.CommonUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author: lizc
 * @date: 2019-04-11 14:54
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class OneToOneTest
{
    @Autowired
    private UserService userService;

    @Autowired
    private OfficeService officeService;

    @Autowired
    private CommonUserService commonUserService;

    @Test
    public void testSaveOffice()
    {
        Office office = new Office();
        office.setName("阿里巴巴");
        office.setAddress("浙江杭州");
        officeService.save(office);
    }

    /*
     * @Test public void testSaveUser() { CommonUser commonUser = new CommonUser();
     * commonUser.setName("龙葵"); Example example = Example.of(commonUser); List<CommonUser>
     * commonUsers = commonUserService.findAll(example); User user = new User();
     * user.setCommonUser(commonUsers.get(0)); user.setPhone("15662410583");
     * user.setOffice(officeService.get("402881ef6a0b3f0a016a0b3f2d9c0000"));
     * userService.save(user); }
     */

    @Test
    public void updateUser()
    {
        User user = userService.get("402881ef6a0b45de016a0b46043b0000");
        Office office = officeService.get("402881ef6a0b3f0a016a0b3f2d9c0000");
        CommonUser commonUser = commonUserService.get("402881ef6a0a3991016a0a39a7da0000");
        office.setName("");
        commonUser.setUserName("");
        commonUser.setPassword("");
        user.setOffice(office);
        user.setCommonUser(commonUser);
        user.setPhone("8888888888");
        userService.save(user);
    }
}
