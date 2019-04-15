package com.lizc.sports.common.service;

import com.lizc.sports.common.entity.BaseEntity;
import com.lizc.sports.common.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import java.io.Serializable;
import java.util.List;

/**
 * T:实体类类型<p>
 * ID:实体类主键的类型<p>
 * R:实现了JpaRepository的实体类Repository<p>
 * @author   lizc@sdhuijin.cn
 * @date     2019/04/10
*/
public abstract class BaseService<T extends BaseEntity,ID extends Serializable,R extends BaseRepository<T,ID>>{

    @Autowired(required = false)
    private R repostitory;

    /**
     * 刷新数据流
     */
    public void flush()
    {
        repostitory.flush();
    }

    public T get(ID id)
    {
        return repostitory.findById(id).orElse(null);
    }

    public void save(T t)
    {
        repostitory.save(t);
    }

    public void saveAndFlush(T t)
    {
        repostitory.saveAndFlush(t);
    }

    /**
     * 逻辑删除
     * @param t 实体类
     */
    public void delete(T t)
    {
        t.setDelFlag(BaseEntity.DEL_FLAG_DELETE);
        save(t);
    }

    /**
     * 逻辑删除
     * @param  id 实体类id
     */
    public void delete(ID id)
    {
        T t = get(id);
        t.setDelFlag(BaseEntity.DEL_FLAG_DELETE);
        save(t);
    }


    /**
     * 物理删除
     * @param t 实体类
     */
    public void forceDelete(T t)
    {
        repostitory.delete(t);
    }

    /**
     * 找到所有实体
     */
    public List<T> findAll()
    {
        return repostitory.findAll();
    }

    /**
     * 根据example找到所有实体
     */
    public List<T> findAll(Example<T> example) {
        return repostitory.findAll(example);
    }

    /**
     * 如果该id实体存在，返回true，否则返回false
     */
    public boolean exists(ID id)
    {
        return repostitory.existsById(id);
    }

}
