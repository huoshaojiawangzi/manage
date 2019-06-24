package com.lizc.sports.sys.controller;


import com.lizc.sports.common.dto.CurrentUserInfo;
import com.lizc.sports.common.exception.UserOverdueException;
import com.lizc.sports.pc.demo.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lizc.sports.common.dto.JsonResult;
import com.lizc.sports.common.enums.SysResultCode;
import com.lizc.sports.sys.entity.CommonUser;
import com.lizc.sports.sys.sevice.CommonUserService;
import com.lizc.sports.sys.utils.UserUtils;


/**
 * 访问的公共资源
 * 
 * @author lizc@sdhuijin.cn
 * @date 2019/03/06
 */
@RestController
@RequestMapping("/home")
public class LoginController
{
    private final CommonUserService commonUserService;

    @Autowired
    public LoginController(CommonUserService commonUserService) {
        this.commonUserService = commonUserService;
    }

    @RequestMapping("index")
    public String index()
    {
        return "index";
    }

    @PostMapping("test")
    public String test(User user)
    {
        System.out.println(user.getPhone());
        return "亲，您没有访问权限哦!";
    }

    @RequestMapping("unAuth")
    public String loginView()
    {
        return "亲，您没有访问权限哦!";
    }

    /**
     * 用户角色切换
     */
    @RequestMapping("role-switch")
    public JsonResult roleSwitch(int roleIndex)
    {
        JsonResult jsonResult = new JsonResult();
        CommonUser currentUser = UserUtils.getCurrentUser();
        if(roleIndex >= 0 && roleIndex < currentUser.getRoles().size())
        {
            currentUser.setRoleIndex(roleIndex);
            commonUserService.save(currentUser);
            jsonResult.setResultCode(SysResultCode.SUCCESS);
        }
        else
        {
            jsonResult.setResultCode(SysResultCode.FAILURE);
        }
        return jsonResult;
    }

    /**
     * 登录
     */
    @RequestMapping("login")
    public JsonResult<CommonUser> login(String userName, String password)
    {
        JsonResult<CommonUser> jsonResult = new JsonResult<>();
        // 得到subject
        Subject subject = SecurityUtils.getSubject();
        // 封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        try
        {
            subject.login(token);
            jsonResult.setCode(0);
            jsonResult.setMsg("登录成功");
            jsonResult.setResult(UserUtils.getCurrentUser());
        }
        catch (UnknownAccountException e)
        {
            jsonResult.setCode(1);
            jsonResult.setMsg("账号不存在");
        }
        catch (IncorrectCredentialsException e)
        {
            jsonResult.setCode(1);
            jsonResult.setMsg("密码错误");
        }
        return jsonResult;
    }

    /**
     * 获取当前用户菜单以及权限
     */
    @RequestMapping("getMenusAndPermissions")
    public JsonResult<CurrentUserInfo> getMenusAndPermissions()
    {
        if(UserUtils.getCurrentUser()==null)
        {
            throw new UserOverdueException();
        }
        JsonResult<CurrentUserInfo> jsonResult = new JsonResult<>();
        CurrentUserInfo currentUserInfo = new CurrentUserInfo();
        currentUserInfo.setPermissionTree(UserUtils.getCurrentPermissions());
        currentUserInfo.setMenuTree(UserUtils.getCurrentMenus());
        jsonResult.setResultCode(SysResultCode.SUCCESS);
        jsonResult.setResult(currentUserInfo);
        return jsonResult;
    }
}
