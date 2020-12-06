package com.lizc.manage.sys.sevice;


import com.alibaba.fastjson.JSONObject;
import com.lizc.manage.common.entity.BaseEntity;
import com.lizc.manage.common.service.BaseService;
import com.lizc.manage.common.utils.RedisUtils;
import com.lizc.manage.common.utils.StringUtils;
import com.lizc.manage.sys.bo.RoleCache;
import com.lizc.manage.sys.entity.Role;
import com.lizc.manage.sys.repository.RoleRepository;
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
public class RoleService extends BaseService<Role, String, RoleRepository>
{

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

    /**
     * 给搜索增加查询条件
     *
     * @param root            元模型
     * @param criteriaBuilder 查询条件构建器
     * @param predicates      predicate的list稽核
     * @param searchModel     查询模型
     */
    @Override
    protected void setPredicates(Root<Role> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, JSONObject searchModel) {
        predicates.add(
                criteriaBuilder.equal(root.<String> get("delFlag"), BaseEntity.DEL_FLAG_NORMAL));
        if (StringUtils.isNotBlank(searchModel.getString("name")))
        {
            predicates.add(criteriaBuilder.equal(root.<String> get("name"),
                      searchModel.getString("name")));
        }
    }

    public Role findByName(String name){
        JSONObject searchModel = new JSONObject();
        searchModel.put("name",name);
        return findOne(searchModel);
    }
}
