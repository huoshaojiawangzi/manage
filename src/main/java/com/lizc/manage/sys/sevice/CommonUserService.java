package com.lizc.manage.sys.sevice;


import com.lizc.manage.common.service.BaseService;
import com.lizc.manage.sys.entity.CommonUser;
import com.lizc.manage.sys.repository.CommonUserRepository;
import org.springframework.stereotype.Service;


/**
 * 系统用户业务逻辑
 * 
 * @author: lizc
 * @date: 2019-03-06 14:53
 **/
@Service
public class CommonUserService extends BaseService<CommonUser, String, CommonUserRepository>
{}
