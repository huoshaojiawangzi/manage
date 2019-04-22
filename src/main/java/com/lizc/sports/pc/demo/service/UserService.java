package com.lizc.sports.pc.demo.service;


import com.lizc.sports.common.service.PageableBaseService;
import com.lizc.sports.common.utils.StringUtils;
import com.lizc.sports.pc.demo.entity.User;
import com.lizc.sports.pc.demo.repository.UserRepository;
import com.lizc.sports.pc.demo.vo.UserSearchModel;
import com.lizc.sports.sys.sevice.CommonUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserService extends PageableBaseService<User,String,UserSearchModel,UserRepository>
{
    private final UserRepository userRepository;

    private final CommonUserService commonUserService;

    @Autowired
    public UserService(UserRepository userRepository, CommonUserService commonUserService)
    {
        this.userRepository = userRepository;
        this.commonUserService = commonUserService;
    }

    @Override
    public Page<User> findPage(UserSearchModel searchModel)
    {
        return userRepository.findAll(getSpec(searchModel), PageRequest.of(searchModel.getPage() - 1, searchModel.getLimit(),
                searchModel.getSort()));
    }

    private Specification<User> getSpec(UserSearchModel searchModel)
    {
        return (Specification<User>)(root, query, cb) -> {
            List<Predicate> list = new ArrayList<>();
            if(StringUtils.isNotBlank(searchModel.getDelFlag()))
            {
                list.add(cb.equal(root.<String> get("delFlag"), searchModel.getDelFlag()));
            }
            if (StringUtils.isNotBlank(searchModel.getName()))
            {
                list.add(cb.like(root.<String> get("commonUser").get("name"),
                    "%" + searchModel.getName() + "%"));
            }
            if (StringUtils.isNotBlank(searchModel.getUserName()))
            {
                list.add(cb.like(root.<String> get("commonUser").get("userName"),
                    "%" + searchModel.getUserName() + "%"));
            }
            Predicate[] p = new Predicate[list.size()];
            return cb.and(list.toArray(p));
        };
    }

    @Override
    public void delete(String id)
    {
        User user = get(id);
        super.delete(user);
        commonUserService.delete(user.getCommonUser());
    }
}
