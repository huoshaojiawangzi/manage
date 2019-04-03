package com.lizc.sports.pc.demo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
*   访问的公共资源
* @author   lizc@sdhuijin.cn
* @date     2019/03/06
*/
@Controller
@RequestMapping("public")
public class PublicController
{
    @RequestMapping("index")
    public String index()
    {
        return "index";
    }

    @ResponseBody
    @GetMapping("test")
    public String test(String id)
    {
        System.out.println(id);
        return "test111";
    }

    @RequestMapping("loginView")
    public String loginView()
    {
        return "login";
    }
    /**登录
     */
    @RequestMapping("login")
    public String login(String userName,String password,Model model)
    {
        //得到subject
        Subject subject = SecurityUtils.getSubject();
        //封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(userName,password);
        try
        {
            subject.login(token);
            return "index";
        }
        catch (UnknownAccountException e)
        {
            model.addAttribute("msg", "用户名不存在");
            return "login";
        }
        catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", "密码错误");
            return "login";
        }
    }
    @RequestMapping("unAuth")
    public String unAuth()
    {
        return "unAuth";
    }
}
