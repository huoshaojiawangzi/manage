package com.lizc.manage.sys.sevice;


import com.alibaba.fastjson.JSONObject;
import com.lizc.manage.common.service.BaseService;
import com.lizc.manage.sys.entity.Dictionary;
import com.lizc.manage.sys.repository.DictionaryRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;


/**
 * @author: lizc
 * @date: 2019-04-17 13:47
 **/
@Service
public class DictionaryService extends BaseService<Dictionary, String , DictionaryRepository>
{
    @Override
    protected void setPredicates(Root<Dictionary> root, CriteriaBuilder criteriaBuilder,
                                 List<Predicate> predicates, JSONObject searchModel)
    {
        if (StringUtils.isNotBlank(searchModel.getString("type")))
        {
            predicates.add(
                criteriaBuilder.like(root.get("type"), "%" + searchModel.getString("type") + "%"));
        }
        if (StringUtils.isNotBlank(searchModel.getString("label")))
        {
            predicates.add(
                criteriaBuilder.like(root.get("label"), "%" + searchModel.getString("label") + "%"));
        }
        if (StringUtils.isNotBlank(searchModel.getString("remarks")))
        {
            predicates.add(
                criteriaBuilder.like(root.get("remarks"), "%" + searchModel.getString("remarks") + "%"));
        }
    }
}
