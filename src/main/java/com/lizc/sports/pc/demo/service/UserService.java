package com.lizc.sports.pc.demo.service;


import com.lizc.sports.common.service.PageableBaseService;
import com.lizc.sports.common.utils.StringUtils;
import com.lizc.sports.pc.demo.entity.User;
import com.lizc.sports.pc.demo.repository.UserRepository;
import com.lizc.sports.pc.demo.vo.UserSearchModel;
import com.lizc.sports.sys.sevice.CommonUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;


@Service
public class UserService extends PageableBaseService<User, String, UserSearchModel, UserRepository>
{

    private final CommonUserService commonUserService;

    @Autowired
    public UserService(CommonUserService commonUserService)
    {
        this.commonUserService = commonUserService;
    }


    @Override
    public void delete(String id)
    {
        User user = get(id);
        super.delete(user);
        commonUserService.delete(user.getCommonUser());
    }

    @Override
    protected void setPredicates(Root<User> root, CriteriaBuilder cb, List<Predicate> predicates, UserSearchModel searchModel)
    {
        if (StringUtils.isNotBlank(searchModel.getName()))
        {
            predicates.add(cb.like(root.<String> get("commonUser").get("name"),
                    "%" + searchModel.getName() + "%"));
        }
        if (StringUtils.isNotBlank(searchModel.getUserName()))
        {
            predicates.add(cb.like(root.<String> get("commonUser").get("userName"),
                    "%" + searchModel.getUserName() + "%"));
        }
    }
}
