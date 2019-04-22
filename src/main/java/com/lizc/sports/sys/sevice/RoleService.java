package com.lizc.sports.sys.sevice;


import com.lizc.sports.common.service.PageableBaseService;
import com.lizc.sports.common.utils.StringUtils;
import com.lizc.sports.sys.entity.Role;
import com.lizc.sports.sys.repository.RoleRepository;
import com.lizc.sports.sys.vo.RoleSearchModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;


/**
 * 角色控制业务逻辑
 * 
 * @author: lizc@sdhuijin.cn
 * @date: 2019-03-06 15:07
 **/
@Service
public class RoleService extends PageableBaseService<Role, String, RoleSearchModel,RoleRepository>
{
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository)
    {
        this.roleRepository = roleRepository;
    }

    @Override
    public Page<Role> findPage(RoleSearchModel searchModel)
    {
        return roleRepository.findAll(getSpec(searchModel), PageRequest.of(
            searchModel.getPage() - 1, searchModel.getLimit(), searchModel.getSort()));
    }

    private Specification<Role> getSpec(RoleSearchModel searchModel)
    {
        return (Specification<Role>)(root, query, cb) -> {
            List<Predicate> list = new ArrayList<>();
            if(StringUtils.isNotBlank(searchModel.getDelFlag()))
            {
                list.add(cb.equal(root.<String> get("delFlag"), searchModel.getDelFlag()));
            }
            Predicate[] p = new Predicate[list.size()];
            return cb.and(list.toArray(p));
        };
    }
}
