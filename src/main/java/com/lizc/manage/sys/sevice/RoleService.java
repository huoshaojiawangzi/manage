package com.lizc.manage.sys.sevice;


import com.lizc.manage.common.service.PageableBaseService;
import com.lizc.manage.common.utils.RedisUtils;
import com.lizc.manage.sys.bo.RoleCache;
import com.lizc.manage.sys.entity.Role;
import com.lizc.manage.sys.repository.RoleRepository;
import com.lizc.manage.sys.vo.RoleSearchModel;
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
    protected void setPredicates(Root<Role> root, CriteriaBuilder criteriaBuilder,
                                 List<Predicate> predicates, RoleSearchModel searchModel)
    {}

    /**
     * 获取完整的role，包含role中的permissions以及menus
     * 
     * @param id
     *            id
     * @return role
     */
    @Transactional
    public Role getComplete(String id)
    {
        RoleCache roleCache = (RoleCache)RedisUtils.getObj("role:" + id, RoleCache.class);
        Role role;
        if (roleCache != null)
        {
            role = roleCache.transform();
        }
        else
        {
            role = get(id);
            // 后面这两行为了读取懒加载的数据
            role.getPermissions().size();
            role.getMenus().size();
            RedisUtils.setObj("role:" + id, new RoleCache().generate(role));
        }
        return role;
    }

    @Override
    public void save(Role role)
    {
        super.save(role);
        RedisUtils.del("role:" + role.getId());
    }

    @Override
    public void saveAndFlush(Role role)
    {
        super.saveAndFlush(role);
        RedisUtils.del("role:" + role.getId());
    }

    @Override
    public void delete(String id)
    {
        super.delete(id);
    }
}
