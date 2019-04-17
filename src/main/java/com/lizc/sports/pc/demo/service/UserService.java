package com.lizc.sports.pc.demo.service;


import com.lizc.sports.common.service.BaseService;
import com.lizc.sports.common.utils.StringUtils;
import com.lizc.sports.pc.demo.entity.User;
import com.lizc.sports.pc.demo.repository.UserRepository;
import com.lizc.sports.pc.demo.vo.UserSearchModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserService extends BaseService<User, String, UserRepository>
{
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> findPage(UserSearchModel searchModel)
    {
        Pageable pageable = PageRequest.of(searchModel.getPage()-1, searchModel.getLimit());
        Specification<User> specification = getSpec(searchModel);
        return userRepository.findAll(specification,pageable);
    }

    private Specification<User> getSpec(UserSearchModel searchModel)
    {
        return (Specification<User>) (root, query, cb) -> {
            List<Predicate> list = new ArrayList<>();
            if (StringUtils.isNotBlank(searchModel.getName()))
            {
                list.add(cb.like(root.<String>get("commonUser").get("name"),
                        "%" + searchModel.getName() + "%"));
            }
            if(StringUtils.isNotBlank(searchModel.getUserName()))
            {
                list.add(cb.like(root.<String>get("commonUser").get("userName"),
                        "%" + searchModel.getUserName() + "%"));
            }
            Predicate[] p = new Predicate[list.size()];
            return cb.and(list.toArray(p));
        };
    }
}
