package com.lizc.sports.sys.sevice;


import com.lizc.sports.common.service.BaseService;
import com.lizc.sports.sys.entity.Permission;
import com.lizc.sports.sys.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 权限控制业务逻辑
 * 
 * @author: lizc@sdhuijin.cn
 * @date: 2019-03-06 15:14
 **/
@Service
public class PermissionService extends BaseService<Permission, String, PermissionRepository>
{

    private final PermissionRepository permissionRepository;

    @Autowired
    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public List<Permission> findRoots()
    {
        return permissionRepository.findRoots();
    }

}
