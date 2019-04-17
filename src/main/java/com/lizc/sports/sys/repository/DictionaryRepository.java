package com.lizc.sports.sys.repository;

import org.springframework.stereotype.Repository;

import com.lizc.sports.common.repository.BaseRepository;
import com.lizc.sports.sys.entity.Dictionary;

/**
 * @author: lizc@sdhuijin.cn
 * @date: 2019-04-17 13:49
 **/
@Repository
public interface DictionaryRepository extends BaseRepository<Dictionary,String>
{
}
