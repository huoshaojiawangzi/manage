package com.lizc.sports.pc.demo.controller;

import com.lizc.sports.sys.utils.UserUtils;
import com.lizc.sports.pc.demo.entity.User;
import com.lizc.sports.pc.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("home/user")
public class UserController
{
    @Autowired
    private UserService userService;
    @ResponseBody
    @RequestMapping("/save")
    public String userAdd()
    {
        System.out.println("用户增加");
        User user = new User();
        user.setPhone("15662410583");
        user.setCommonUser(UserUtils.getCurrentUser());
        userService.save(user);
        return "success";
    }
    @RequestMapping("userUpdate")
    public String userUpdate()
    {
        return "user/userUpdate";
    }
}
