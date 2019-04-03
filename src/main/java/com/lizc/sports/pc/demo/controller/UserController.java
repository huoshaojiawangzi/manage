package com.lizc.sports.pc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController
{
    @RequestMapping("userAdd")
    public String userAdd()
    {
        return "user/userAdd";
    }
    @RequestMapping("userUpdate")
    public String userUpdate()
    {
        return "user/userUpdate";
    }
}
