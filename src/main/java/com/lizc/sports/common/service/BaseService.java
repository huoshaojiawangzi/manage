package com.lizc.sports.common.service;


import com.lizc.sports.common.entity.BaseEntity;
import com.lizc.sports.common.repository.BaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * T:实体类类型<p> ID:实体类主键的类型<p> R:实现了JpaRepository的实体类Repository<p>
 * 
 * @author lizc@sdhuijin.cn
 * @date 2019/04/10
 */
@SuppressWarnings("ALL")
public abstract class BaseService<T extends BaseEntity, ID extends Serializable, R extends BaseRepository<T, ID>>
{
    private static final Logger logger = LoggerFactory.getLogger(BaseService.class);

    @Autowired(required = false)
    protected R repostitory;

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

    public T get(Example<T> example)
    {
        return repostitory.findOne(example).orElse(null);
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
     * 
     * @param t
     *            实体类
     */
    public void delete(T t)
    {
        t.setDelFlag(BaseEntity.DEL_FLAG_DELETE);
        repostitory.saveAndFlush(t);
    }

    /**
     * 逻辑删除
     * 
     * @param id
     *            实体类id
     */
    public void delete(ID id)
    {
        T t = get(id);
        delete(t);
    }

    /**
     * 物理删除
     * 
     * @param t
     *            实体类
     */
    public void forceDelete(T t)
    {
        repostitory.delete(t);
    }

    /**
     * 找到所有实体(包含已删除的)
     */
    public List<T> findAll()
    {
        return repostitory.findAll();
    }

    /**
     * 根据example找到所有实体
     */
    public List<T> findAll(Example<T> example)
    {
        return repostitory.findAll(example);
    }

    /**
     * 根据specification找到所有实体
     */
    public List<T> findAll(Specification<T> specification)
    {
        return repostitory.findAll(specification);
    };

    /**
     * 找到所有实体(不包含已删除的)
     * 
     * @return
     */
    public List<T> findAllEnable()
    {
        Specification<T> specification = new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(cb.equal(root.<String>get("delFlag"),BaseEntity.DEL_FLAG_NORMAL));
                Predicate[] p = new Predicate[predicates.size()];
                return cb.and(predicates.toArray(p));
            }
        };
        return repostitory.findAll(specification);
    }

    /**
     * 如果该id实体存在，返回true，否则返回false
     */
    public boolean exists(ID id)
    {
        return repostitory.existsById(id);
    }

}
