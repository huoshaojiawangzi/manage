package com.lizc.sports.sys.repository;


import com.lizc.sports.common.repository.BaseRepository;
import com.lizc.sports.sys.entity.Menu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * @author: lizc@sdhuijin.cn
 * @date: 2019-03-06 11:15
 **/
@Repository
public interface MenuRepository extends BaseRepository<Menu, String>
{}
