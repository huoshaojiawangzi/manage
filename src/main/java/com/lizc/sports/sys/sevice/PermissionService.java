package com.lizc.sports.sys.sevice;

import com.lizc.sports.sys.entity.Permission;
import com.lizc.sports.sys.entity.Role;
import com.lizc.sports.sys.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import java.util.HashSet;
import java.util.Set;

/**权限控制业务逻辑
 * @author: lizc@sdhuijin.cn
 * @date: 2019-03-06 15:14
 **/
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RoleService roleService;

    @Cacheable(value = "role", key = "#roleId")
    public Set<String> getPermissions(String roleId) throws Exception {
        Role r = roleService.get(roleId);
        Set<String> perms = new HashSet<>();
        for (Permission p : r.getPermissions()) {
            fill(perms, p);
        }
        return perms;
    }

    private void fill(Set<String> perms, Permission p) {
        if (!perms.contains(p.getId())) {
            perms.add(p.getId());
            for (Permission ps : p.getChildren()) {
                fill(perms, ps);
            }
        }
    }
}
