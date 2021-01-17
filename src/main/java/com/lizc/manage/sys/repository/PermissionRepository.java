package com.lizc.manage.sys.repository;


import com.lizc.manage.common.repository.BaseRepository;
import com.lizc.manage.sys.entity.Permission;
import org.springframework.stereotype.Repository;


/**
 * @author: lizc
 * @date: 2019-03-06 11:15
 **/
@Repository
public interface PermissionRepository extends BaseRepository<Permission, String>
{}
