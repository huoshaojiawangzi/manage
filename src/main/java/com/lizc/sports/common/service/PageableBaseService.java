package com.lizc.sports.common.service;

import com.lizc.sports.common.entity.BaseEntity;
import com.lizc.sports.common.repository.BaseRepository;
import com.lizc.sports.common.vo.BaseSearchModel;
import org.springframework.data.domain.Page;

import java.io.Serializable;

/**需要分页查询的BaseService
 * @author: lizc@sdhuijin.cn
 * @date: 2019-04-22 16:32
 **/
public abstract class PageableBaseService<T extends BaseEntity, ID extends Serializable, M extends BaseSearchModel, R extends BaseRepository<T, ID>> extends BaseService<T,ID,R>
{
    /**
     * 获取分页
     * @param m 查询模型
     * @return Page
     */
    public abstract Page<T> findPage(M m);
}
