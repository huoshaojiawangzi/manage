package com.lizc.manage.sys.sevice;


import com.lizc.manage.common.service.TreeBaseService;
import com.lizc.manage.sys.entity.Permission;
import com.lizc.manage.sys.repository.PermissionRepository;
import org.springframework.stereotype.Service;


/**
 * 权限控制业务逻辑
 * 
 * @author: lizc
 * @date: 2019-03-06 15:14
 **/
@Service
public class PermissionService extends TreeBaseService<Permission, String, PermissionRepository>
{}
