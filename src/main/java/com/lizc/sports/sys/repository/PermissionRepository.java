package com.lizc.sports.sys.repository;

import com.lizc.sports.sys.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author: lizc@sdhuijin.cn
 * @date: 2019-03-06 11:15
 **/
public interface PermissionRepository extends JpaRepository<Permission, String>, JpaSpecificationExecutor<Permission> {

}
