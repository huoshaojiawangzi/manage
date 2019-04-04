package com.lizc.sports.sys.sevice;

import com.lizc.sports.sys.entity.Menu;
import com.lizc.sports.sys.entity.Permission;
import com.lizc.sports.sys.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**权限控制业务逻辑
 * @author: lizc@sdhuijin.cn
 * @date: 2019-03-06 15:14
 **/
@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public Permission get(String id)
    {
        return permissionRepository.findById(id).get();
    }

    public void save(Permission permission)
    {
        permissionRepository.save(permission);
    }

    public void saveAndFlush(Permission permission)
    {
        permissionRepository.saveAndFlush(permission);
    }

    public void delete(Permission permission)
    {
        permissionRepository.delete(permission);
    }

    public List<Permission> findAll()
    {
        return permissionRepository.findAll();
    }

    @Transactional
    public List<Menu> findMenusByPermission(String id)
    {
        Permission permission = get(id);
        return permission.getMenus();
    }

}
