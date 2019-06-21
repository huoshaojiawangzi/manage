package com.lizc.sports.pc.demo.service;


import com.lizc.sports.common.entity.BaseEntity;
import com.lizc.sports.common.exception.MsgException;
import com.lizc.sports.common.service.TreeBaseService;
import com.lizc.sports.pc.demo.entity.Office;
import com.lizc.sports.pc.demo.entity.User;
import com.lizc.sports.pc.demo.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;


@Service
public class OfficeService extends TreeBaseService<Office, String, OfficeRepository>
{

    private final UserService userService;

    @Autowired
    public OfficeService(UserService userService) {
        this.userService = userService;
    }

    public void deleteOffice(String id) throws MsgException
    {
        Specification<User> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("office").get("id"),id));
            predicates.add(criteriaBuilder.equal(root.get("delFlag"), BaseEntity.DEL_FLAG_NORMAL));
            Predicate[] p = new Predicate[predicates.size()];
            return criteriaBuilder.and(predicates.toArray(p));
        };
        List<User> users = userService.findAll(specification);
        if(!users.isEmpty()){
            throw new MsgException("该机构下存在用户，无法删除");
        }else
        {
            super.delete(id);
        }
    }
}
