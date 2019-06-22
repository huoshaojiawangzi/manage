package com.lizc.sports.sys.sevice;


import com.lizc.sports.common.service.BaseService;
import com.lizc.sports.sys.entity.CommonUser;
import com.lizc.sports.sys.repository.CommonUserRepository;
import org.springframework.stereotype.Service;


/**
 * 系统用户业务逻辑
 * 
 * @author: lizc@sdhuijin.cn
 * @date: 2019-03-06 14:53
 **/
@Service
public class CommonUserService extends BaseService<CommonUser, String, CommonUserRepository>
{

    public CommonUser findByUserName(String userName)
    {
        return repostitory.findByUserName(userName);
    }
}
