package com.lizc.manage.common.dto;


import com.lizc.manage.sys.entity.Menu;
import com.lizc.manage.sys.entity.Permission;
import lombok.Data;

import java.util.List;


/**
 * 包含当前用户的菜单以及权限
 * 
 * @author: lizc@sdhuijin.cn
 * @date: 2019-05-08 14:54
 **/
@Data
public class CurrentUserInfo
{
    List<Menu> menuTree;

    List<Permission> permissionTree;
}
