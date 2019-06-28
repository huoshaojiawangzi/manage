package com.lizc.manage.pc.user.service;


import com.lizc.manage.common.service.PageableBaseService;
import com.lizc.manage.common.utils.StringUtils;
import com.lizc.manage.pc.user.entity.User;
import com.lizc.manage.pc.user.repository.UserRepository;
import com.lizc.manage.pc.user.vo.UserSearchModel;
import com.lizc.manage.sys.sevice.CommonUserService;
import com.lizc.manage.sys.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void save(User user)
    {
        if(StringUtils.isBlank(user.getId()))
        {
            user.getCommonUser().setPassword(MD5.md5(user.getCommonUser().getPassword()));
        }
        else
        {
            User oldUser = get(user.getId());
            if(!user.getCommonUser().getPassword().equals(oldUser.getCommonUser().getPassword()))
            {
                user.getCommonUser().setPassword(MD5.md5(user.getCommonUser().getPassword()));
            }
        }
        super.save(user);
    }

    @Override
    @Transactional
    public void delete(String id)
    {
        User user = get(id);
        super.delete(user);
        commonUserService.delete(user.getCommonUser());
    }

    @Override
    protected void setPredicates(Root<User> root, CriteriaBuilder criteriaBuilder,
                                 List<Predicate> predicates, UserSearchModel searchModel)
    {
        if (StringUtils.isNotBlank(searchModel.getName()))
        {
            predicates.add(criteriaBuilder.like(root.<String> get("commonUser").get("name"),
                "%" + searchModel.getName() + "%"));
        }
        if (StringUtils.isNotBlank(searchModel.getUserName()))
        {
            predicates.add(criteriaBuilder.like(root.<String> get("commonUser").get("userName"),
                "%" + searchModel.getUserName() + "%"));
        }
        if (StringUtils.isNotBlank(searchModel.getOfficeName()))
        {
            predicates.add(criteriaBuilder.like(root.<String> get("office").get("name"),
                "%" + searchModel.getOfficeName() + "%"));
        }
    }
}
