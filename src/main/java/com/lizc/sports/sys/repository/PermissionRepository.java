package com.lizc.sports.sys.repository;


import com.lizc.sports.common.repository.BaseRepository;
import com.lizc.sports.sys.entity.Permission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * @author: lizc@sdhuijin.cn
 * @date: 2019-03-06 11:15
 **/
@Repository
public interface PermissionRepository extends BaseRepository<Permission, String>
{
    @Query(value = "select p from Permission p where p.name = :name and p.delFlag = 0")
    Permission findByName(@Param("name") String name);

    @Query(value = "select p from Permission p where p.url = :url and p.delFlag = 0")
    Permission findByUrl(@Param("url") String url);

    @Query(value = "select p from Permission p where p.tag = :tag and p.delFlag = 0")
    Permission findByTag(@Param("tag") String tag);
}
