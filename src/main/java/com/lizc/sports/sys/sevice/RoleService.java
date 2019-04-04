package com.lizc.sports.sys.sevice;

import com.lizc.sports.common.utils.StringUtils;
import com.lizc.sports.sys.entity.Role;
import com.lizc.sports.sys.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**角色控制业务逻辑
 * @author: lizc@sdhuijin.cn
 * @date: 2019-03-06 15:07
 **/
@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role get(String id)  {
        return roleRepository.findById(id).get();
    }
    public List<Role> findAll()
    {
        return roleRepository.findAll();
    }
}
