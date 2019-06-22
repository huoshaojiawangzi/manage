package com.lizc.sports.sys.sevice;


import com.lizc.sports.common.service.TreeBaseService;
import com.lizc.sports.sys.entity.Permission;
import com.lizc.sports.sys.repository.PermissionRepository;
import org.springframework.stereotype.Service;


/**
 * 权限控制业务逻辑
 * 
 * @author: lizc@sdhuijin.cn
 * @date: 2019-03-06 15:14
 **/
@Service
public class PermissionService extends TreeBaseService<Permission, String, PermissionRepository>
{}
