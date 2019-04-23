package com.lizc.sports.common.service;


import com.lizc.sports.common.entity.BaseEntity;
import com.lizc.sports.common.repository.BaseRepository;

import java.io.Serializable;
import java.util.List;


/**
 * 需要分页查询的BaseService
 * 
 * @author: lizc@sdhuijin.cn
 * @date: 2019-04-22 16:32
 **/
public abstract class TreeBaseService<T extends BaseEntity, ID extends Serializable, R extends BaseRepository<T, ID>> extends BaseService<T, ID, R>
{
    /**
     * 获取所有根节点
     * 
     * @return 实体类集合List
     */
    public abstract List<T> findRoots();
}
