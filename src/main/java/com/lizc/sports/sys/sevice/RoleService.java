package com.lizc.sports.sys.sevice;


import com.lizc.sports.common.service.PageableBaseService;
import com.lizc.sports.sys.entity.Role;
import com.lizc.sports.sys.repository.RoleRepository;
import com.lizc.sports.sys.vo.RoleSearchModel;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;


/**
 * 角色控制业务逻辑
 * 
 * @author: lizc@sdhuijin.cn
 * @date: 2019-03-06 15:07
 **/
@Service
public class RoleService extends PageableBaseService<Role, String, RoleSearchModel, RoleRepository>
{
    @Override
    protected void setPredicates(Root<Role> root, CriteriaBuilder cb, List<Predicate> predicates, RoleSearchModel searchModel) {

    }

    /**
     * 获取完整的role，包含role中的permissions以及menus
     * @param id id
     * @return role
     */
    @Cacheable(value = "role",key = "#id")
    @Transactional
    public Role getComplete(String id)
    {
        Role role = get(id);
        role.getPermissions();
        role.getMenus();
        return role;
    }
}
