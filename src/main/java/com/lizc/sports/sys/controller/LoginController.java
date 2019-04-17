package com.lizc.sports.sys.controller;


import com.lizc.sports.common.controller.BaseController;
import com.lizc.sports.common.dto.JsonResult;
import com.lizc.sports.common.enums.SysResultCode;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 访问的公共资源
 * 
 * @author lizc@sdhuijin.cn
 * @date 2019/03/06
 */
@RestController
@RequestMapping("home")
public class LoginController extends BaseController
{

    private final PermissionService permissionService;

    private final MenuService menuService;

    @Autowired
    public LoginController(PermissionService permissionService, MenuService menuService)
    {
        this.permissionService = permissionService;
        this.menuService = menuService;
    }

    @RequestMapping("index")
    public String index()
    {
        return "index";
    }

    @GetMapping("find-permissions")
    public List<Permission> getPermissions()
    {
        return permissionService.findAll();
    }

    @GetMapping("find-menus")
    public List<Menu> getMenus()
    {
        return menuService.findAll();
    }

    @RequestMapping("unAuth")
    public String loginView()
    {
        return "亲，您没有访问权限哦!";
    }

    /**
     * 登录
     */
    @RequestMapping("login")
    public JsonResult<String> login(String userName, String password)
    {
        JsonResult<String> jsonResult = new JsonResult<>();
        // 得到subject
        Subject subject = SecurityUtils.getSubject();
        // 封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        try
        {
            subject.login(token);
            jsonResult.setResultCode(SysResultCode.SUCCESS);
            jsonResult.setResult("登录成功！");
        }
        catch (UnknownAccountException e)
        {
            jsonResult.setResultCode(SysResultCode.FAILURE);
            jsonResult.setResult("账号不存在！");
        }
        catch (IncorrectCredentialsException e)
        {
            jsonResult.setResultCode(SysResultCode.FAILURE);
            jsonResult.setResult("密码错误！");
        }
        return jsonResult;
    }
}
