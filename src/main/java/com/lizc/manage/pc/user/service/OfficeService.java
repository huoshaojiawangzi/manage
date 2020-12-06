package com.lizc.manage.pc.user.service;


import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.lizc.manage.common.entity.BaseEntity;
import com.lizc.manage.common.exception.MsgException;
import com.lizc.manage.common.service.TreeBaseService;
import com.lizc.manage.common.utils.StringUtils;
import com.lizc.manage.pc.user.entity.Office;
import com.lizc.manage.pc.user.entity.User;
import com.lizc.manage.pc.user.repository.OfficeRepository;
import com.lizc.manage.sys.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


@Service
public class OfficeService extends TreeBaseService<Office, String, OfficeRepository>
{

    private final UserService userService;

    @Autowired
    public OfficeService(UserService userService)
    {
        this.userService = userService;
    }

    public void deleteOffice(String id)
        throws MsgException
    {
        JSONObject searchModel = new JSONObject();
        searchModel.put("officeId",id);
        List<User> users = userService.findAll(searchModel);
        if (!users.isEmpty())
        {
            throw new MsgException("该机构下存在用户，无法删除");
        }
        else
        {
            super.delete(id);
        }
    }

    /**
     * 给搜索增加查询条件
     *
     * @param root            元模型
     * @param criteriaBuilder 查询条件构建器
     * @param predicates      predicate的list稽核
     * @param searchModel
     */
    @Override
    protected void setPredicates(Root<Office> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, JSONObject searchModel) {
        predicates.add(
                criteriaBuilder.equal(root.<String> get("delFlag"),BaseEntity.DEL_FLAG_NORMAL));
        if(StringUtils.isNotBlank(searchModel.getString("code"))){
            criteriaBuilder.equal(root.<String>get("code"),searchModel.getString("code"));
        }
    }

    public Office findByCode(String code){
        JSONObject searchModel = new JSONObject();
        searchModel.put("code",code);
        return findOne(searchModel);
    }
}
