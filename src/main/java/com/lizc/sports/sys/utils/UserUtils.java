package com.lizc.sports.sys.utils;

import com.lizc.sports.sys.entity.CommonUser;
import com.lizc.sports.sys.entity.Role;
import com.lizc.sports.sys.sevice.CommonUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: lizc@sdhuijin.cn
 * @date: 2019-04-15 16:49
 **/
public class UserUtils {

    @Autowired
    private CommonUserService commonUserService;

    private UserUtils(){}

    public static CommonUser getCurrentUser()
    {
        Subject subject = SecurityUtils.getSubject();
        //得到的其实是认证逻辑返回的SimpleAuthenticationInfo(commonUser,password,"")commonUser)
        return (CommonUser)subject.getPrincipal();
    }
    public static Role getCurrentRole()
    {
        return getCurrentUser().getRoles().get(0);
    }
}
