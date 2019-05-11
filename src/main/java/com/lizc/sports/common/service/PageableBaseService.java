package com.lizc.sports.common.service;


import com.lizc.sports.common.entity.BaseEntity;
import com.lizc.sports.common.repository.BaseRepository;
import org.apache.commons.lang3.StringUtils;
import com.lizc.sports.common.vo.BaseSearchModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 需要分页查询的BaseService
 * 
 * @author: lizc@sdhuijin.cn
 * @date: 2019-04-22 16:32
 **/
public abstract class PageableBaseService<T extends BaseEntity, ID extends Serializable, M extends BaseSearchModel, R extends BaseRepository<T, ID>> extends BaseService<T, ID, R>
{
    /**
     * 获取分页
     * 
     * @param m
     *            查询模型
     * @return Page
     */
    public Page<T> findPage(M m)
    {
        return repostitory.findAll(getSpec(m),
            PageRequest.of(m.getPage() - 1, m.getLimit(), m.getSort()));
    }

    /**
     * 根据model中的查询条件获取spec
     * @param m
     *            查询模型
     * @return Specification
     */
    private Specification<T> getSpec(M m)
    {
        return (Specification<T>) (root, query, criteriaBuilder)->
        {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(m.getDelFlag()))
            {
                predicates.add(criteriaBuilder.equal(root.<String> get("delFlag"), m.getDelFlag()));
            }
            setPredicates(root,criteriaBuilder,predicates,m);
            Predicate[] p = new Predicate[predicates.size()];
            return criteriaBuilder.and(predicates.toArray(p));
        };
    }

    /**
     * 给搜索增加筛选条件
     */
    protected abstract void setPredicates(Root<T> root, CriteriaBuilder criteriaBuilder,List<Predicate> predicates, M m);
}
