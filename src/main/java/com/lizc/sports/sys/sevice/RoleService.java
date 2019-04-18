package com.lizc.sports.sys.sevice;


import com.lizc.sports.sys.vo.RoleSearchModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lizc.sports.common.service.BaseService;
import com.lizc.sports.sys.entity.Role;
import com.lizc.sports.sys.repository.RoleRepository;


/**
 * 角色控制业务逻辑
 * 
 * @author: lizc@sdhuijin.cn
 * @date: 2019-03-06 15:07
 **/
@Service
public class RoleService extends BaseService<Role, String, RoleRepository>
{
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Page<Role> findPage(RoleSearchModel searchModel)
    {
        Pageable pageable = PageRequest.of(searchModel.getPage()-1,searchModel.getLimit());
        return roleRepository.findAll(pageable);
    }
}
