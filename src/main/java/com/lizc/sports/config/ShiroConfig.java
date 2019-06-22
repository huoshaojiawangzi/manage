package com.lizc.sports.config;


import com.lizc.sports.common.utils.StringUtils;
import com.lizc.sports.sys.entity.Permission;
import com.lizc.sports.sys.security.SystemAuthorizingRealm;
import com.lizc.sports.sys.sevice.PermissionService;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Configuration
public class ShiroConfig
{
    private final PermissionService permissionService;

    @Autowired
    public ShiroConfig(PermissionService permissionService)
    {
        this.permissionService = permissionService;
    }

    private void setPermissions(Map<String, String> filterMap, List<Permission> permissions)
    {
        for (Permission permission : permissions)
        {
            if (StringUtils.isNotBlank(permission.getUrl()))
            {
                filterMap.put(permission.getUrl(), "perms[" + permission.getTag() + "]");
            }
            if (!permission.getChildren().isEmpty())
            {
                setPermissions(filterMap, permission.getChildren());
            }
        }
    }

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager)
    {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        Map<String, String> filterMap = new LinkedHashMap<>();
        // 将public路径下的访问放行，注意必须放在代码行数最前面
        filterMap.put("/home/*", "anon");
        // 设置访问权限
        List<Permission> permissions = permissionService.findRoots();
        setPermissions(filterMap, permissions);
        filterMap.put("/home/permission/test", "perms[sys:test]");
        // 将所有的路径进行登录认证权限过滤
        filterMap.put("/**", "authc");
        // 登录认证失败跳转页面
        //shiroFilterFactoryBean.setLoginUrl("/home/loginView");
        // 访问权限认证失败跳转页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/home/unAuth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 配置DefaultWebSecurityManager
     *
     * @param systemAuthorizingRealm
     * @return
     */
    @Bean("defaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("systemAuthorizingRealm") SystemAuthorizingRealm systemAuthorizingRealm)
    {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(systemAuthorizingRealm);
        return defaultWebSecurityManager;
    }

    /**
     * 配置Realm
     */
    @Bean("systemAuthorizingRealm")
    public SystemAuthorizingRealm getRealm()
    {
        return new SystemAuthorizingRealm();
    }

}
