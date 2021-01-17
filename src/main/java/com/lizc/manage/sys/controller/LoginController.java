package com.lizc.manage.sys.controller;


import com.lizc.manage.common.dto.CurrentUserInfo;
import com.lizc.manage.common.dto.JsonResult;
import com.lizc.manage.common.enums.SysResultCode;
import com.lizc.manage.common.exception.UserOverdueException;
import com.lizc.manage.common.utils.MD5;
import com.lizc.manage.sys.entity.CommonUser;
import com.lizc.manage.sys.sevice.CommonUserService;
import com.lizc.manage.sys.utils.UserUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 访问的公共资源
 * 
 * @author lizc
 * @date 2019/03/06
 */
@RestController
@RequestMapping("/home")
public class LoginController
{
    private final CommonUserService commonUserService;

    @Autowired
    public LoginController(CommonUserService commonUserService)
    {
        this.commonUserService = commonUserService;
    }

    /**
     * 用户角色切换
     */
    @RequestMapping("/role-switch")
    public JsonResult roleSwitch(int roleIndex)
    {
        JsonResult jsonResult = new JsonResult();
        CommonUser currentUser = UserUtils.getCurrentUser();
        if (roleIndex >= 0 && roleIndex < currentUser.getRoles().size())
        {
            currentUser.setRoleIndex(roleIndex);
            commonUserService.save(currentUser);
            Subject subject = SecurityUtils.getSubject();
            PrincipalCollection principalCollection = subject.getPrincipals();
            String realmName = principalCollection.getRealmNames().iterator().next();
            PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(currentUser,
                realmName);
            subject.runAs(newPrincipalCollection);
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
    @RequestMapping("/login")
    public JsonResult<CommonUser> login(String userName, String password)
    {
        JsonResult<CommonUser> jsonResult = new JsonResult<>();
        // 得到subject
        Subject subject = SecurityUtils.getSubject();
        // 封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(userName, MD5.md5(password));
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
     * 退出登录
     */
    @RequestMapping("/logout")
    public JsonResult logout()
    {
        JsonResult jsonResult = new JsonResult<>();
        // 得到subject
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return jsonResult;
    }

    /**
     * 获取当前用户菜单以及权限
     */
    @RequestMapping("/getMenusAndPermissions")
    public JsonResult<CurrentUserInfo> getMenusAndPermissions()
    {
        if (UserUtils.getCurrentUser() == null)
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

    /**
     * 用户未登录
     */
    @RequestMapping("/unLogin")
    public JsonResult unLogin()
    {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setResultCode(SysResultCode.UNLOGIN);
        return jsonResult;
    }

    /**
     * 用户权限不足
     */
    @RequestMapping("/unAuth")
    public JsonResult unAuth()
    {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setResultCode(SysResultCode.UNAUTH);
        return jsonResult;
    }
}
