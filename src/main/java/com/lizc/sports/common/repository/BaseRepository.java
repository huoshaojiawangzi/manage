package com.lizc.sports.common.repository;


import com.lizc.sports.common.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;


/**
 * @author: lizc@sdhuijin.cn
 * @date: 2019-04-10 12:42
 **/
@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T>
{
}
