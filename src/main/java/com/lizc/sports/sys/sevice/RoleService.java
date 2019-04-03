package com.lizc.sports.sys.sevice;

import com.lizc.sports.common.utils.StringUtils;
import com.lizc.sports.sys.entity.Role;
import com.lizc.sports.sys.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**角色控制业务逻辑
 * @author: lizc@sdhuijin.cn
 * @date: 2019-03-06 15:07
 **/
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role get(String id) throws Exception {
        if(StringUtils.isBlank(id))
        {
            throw new Exception("获得Role实体失败，id不可为空");
        }
        try
        {
            return roleRepository.findById(id).get();
        }
        catch (Exception e)
        {
            throw new Exception("获得Role实体失败，该id异常");
        }
    }
    public List<Role> findAll()
    {
        return roleRepository.findAll();
    }
}
