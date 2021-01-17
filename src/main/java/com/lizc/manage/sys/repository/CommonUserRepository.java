package com.lizc.manage.sys.repository;


import org.springframework.stereotype.Repository;

import com.lizc.manage.common.repository.BaseRepository;
import com.lizc.manage.sys.entity.CommonUser;


/**
 * @author: lizc
 * @date: 2019-03-06 11:15
 **/
@Repository
public interface CommonUserRepository extends BaseRepository<CommonUser, String>
{}
