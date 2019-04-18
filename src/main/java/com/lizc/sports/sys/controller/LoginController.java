package com.lizc.sports.sys.controller;


import com.lizc.sports.common.dto.JsonResult;
import com.lizc.sports.common.enums.SysResultCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 访问的公共资源
 * 
 * @author lizc@sdhuijin.cn
 * @date 2019/03/06
 */
@RestController
@RequestMapping("home")
public class LoginController
{

    @RequestMapping("index")
    public String index()
    {
        return "index";
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
