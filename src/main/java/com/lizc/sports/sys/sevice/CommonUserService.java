package com.lizc.sports.sys.sevice;


import com.lizc.sports.common.service.BaseService;
import com.lizc.sports.sys.entity.CommonUser;
import com.lizc.sports.sys.entity.Role;
import com.lizc.sports.sys.repository.CommonUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


/**
 * 系统用户业务逻辑
 * 
 * @author: lizc@sdhuijin.cn
 * @date: 2019-03-06 14:53
 **/
@Service
public class CommonUserService extends BaseService<CommonUser, String, CommonUserRepository>
{

    private final CommonUserRepository commonUserRepository;

    @Autowired
    public CommonUserService(CommonUserRepository commonUserRepository)
    {
        this.commonUserRepository = commonUserRepository;
    }

    @Cacheable(value = "commonUser",key = "'userName_'+#userName")
    @Transactional
    public CommonUser findByUserName(String userName)
    {
        CommonUser commonUser = commonUserRepository.findByUserName(userName);
        for (Role role : commonUser.getRoles())
        {
            if (role.getPermissions().isEmpty())
            {
                role.setPermissions(null);
            }
        }
        return commonUser;
    }
}
