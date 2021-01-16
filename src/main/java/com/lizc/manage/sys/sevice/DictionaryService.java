package com.lizc.manage.sys.sevice;


import com.lizc.manage.common.service.PageableBaseService;
import com.lizc.manage.sys.entity.Dictionary;
import com.lizc.manage.sys.repository.DictionaryRepository;
import com.lizc.manage.sys.vo.DictionarySearchModel;
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
public class DictionaryService extends PageableBaseService<Dictionary, String, DictionarySearchModel, DictionaryRepository>
{
    @Override
    protected void setPredicates(Root<Dictionary> root, CriteriaBuilder criteriaBuilder,
                                 List<Predicate> predicates, DictionarySearchModel searchModel)
    {
        if (StringUtils.isNotBlank(searchModel.getType()))
        {
            predicates.add(
                criteriaBuilder.like(root.get("type"), "%" + searchModel.getType() + "%"));
        }
        if (StringUtils.isNotBlank(searchModel.getLabel()))
        {
            predicates.add(
                criteriaBuilder.like(root.get("label"), "%" + searchModel.getLabel() + "%"));
        }
        if (StringUtils.isNotBlank(searchModel.getRemarks()))
        {
            predicates.add(
                criteriaBuilder.like(root.get("remarks"), "%" + searchModel.getRemarks() + "%"));
        }
    }
}
