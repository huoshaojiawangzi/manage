package com.lizc.sports.sys.sevice;


import com.lizc.sports.common.service.PageableBaseService;
import com.lizc.sports.sys.entity.Dictionary;
import com.lizc.sports.sys.repository.DictionaryRepository;
import com.lizc.sports.sys.vo.DictionarySearchModel;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;


/**
 * @author: lizc@sdhuijin.cn
 * @date: 2019-04-17 13:47
 **/
@Service
public class DictionaryService extends PageableBaseService<Dictionary, String, DictionarySearchModel, DictionaryRepository>
{
    @Override
    protected void setPredicates(Root<Dictionary> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, DictionarySearchModel searchModel)
    {
    }
}
