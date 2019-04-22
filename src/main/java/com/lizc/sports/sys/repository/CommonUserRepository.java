package com.lizc.sports.sys.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lizc.sports.common.repository.BaseRepository;
import com.lizc.sports.sys.entity.CommonUser;


/**
 * @author: lizc@sdhuijin.cn
 * @date: 2019-03-06 11:15
 **/
@Repository
public interface CommonUserRepository extends BaseRepository<CommonUser, String>
{
    @Query(value = "select u from CommonUser u where u.userName = :userName and u.delFlag = 0")
    CommonUser findByUserName(@Param("userName") String userName);
}
