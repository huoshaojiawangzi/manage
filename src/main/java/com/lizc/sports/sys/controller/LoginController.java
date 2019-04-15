package com.lizc.sports.sys.controller;

import com.lizc.sports.common.controller.BaseController;
import com.lizc.sports.sys.entity.Menu;
import com.lizc.sports.sys.entity.Permission;
import com.lizc.sports.sys.sevice.MenuService;
import com.lizc.sports.sys.sevice.PermissionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
*   访问的公共资源
* @author   lizc@sdhuijin.cn
* @date     2019/03/06
*/
@Controller
@RequestMapping("home")
public class LoginController extends BaseController
{

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private MenuService menuService;

    @RequestMapping("index")
    public String index()
    {
        return "index";
    }


    @ResponseBody
    @GetMapping("find-permissions")
    public List<Permission> getPermissions()
    {
        return permissionService.findAll();
    }

    @ResponseBody
    @GetMapping("find-menus")
    public List<Menu> getMenus()
    {
        return menuService.findAll();
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
        catch (IncorrectCredentialsException e)
        {
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
