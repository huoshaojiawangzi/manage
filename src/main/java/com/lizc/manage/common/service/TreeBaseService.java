package com.lizc.manage.common.service;


import com.lizc.manage.common.entity.TreeBaseEntity;
import com.lizc.manage.common.repository.BaseRepository;
import com.lizc.manage.common.utils.RedisUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;


/**
 * 需要分页查询的BaseService
 * 
 * @author: lizc
 * @date: 2019-04-22 16:32
 **/
@SuppressWarnings("ALL")
public abstract class TreeBaseService<T extends TreeBaseEntity, ID extends Serializable, R extends BaseRepository<T, ID>> extends BaseService<T, ID, R>
{
    /**
     * 获取所有根节点，不包含已删除的节点
     * 
     * @return List-根节点集合
     */
    public List<T> findRoots()
    {
        List<T> roots = RedisUtils.getList("treeList:" + clazz.toString(), clazz);
        if (roots == null)
        {
            roots = findAllRoots();
            filterTree(roots, findAllEnable());
            RedisUtils.setList("treeList:"+clazz.toString(),roots);
        }
        return roots;
    }

    /**
     * 得到根节点集合，包括已删除的
     * 
     * @return List-根节点集合
     */
    private List<T> findAllRoots()
    {
        Specification<T> specification = (Specification<T>)(root, query, criteriaBuilder) -> {
            query.where(criteriaBuilder.isNull(root.get("parent")));
            query.orderBy(criteriaBuilder.asc(root.get("sort")));
            return query.getRestriction();
        };
        return repostitory.findAll(specification);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(T t)
    {
        super.save(t);
        RedisUtils.del("treeList:" + clazz.toString());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveAndFlush(T t)
    {
        super.saveAndFlush(t);
        RedisUtils.del("treeList:" + clazz.toString());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(T t)
    {
        super.delete(t);
        RedisUtils.del("treeList:" + clazz.toString());
    }

    /**
     * 获取所有根节点，并根据比对节点集合过滤掉不需要的节点
     * 
     * @param compareList
     *            比对节点集合，包含重复数据
     * @return 过滤后的根节点
     */
    public List<T> findFilterRoots(List<T> compareList)
    {
        List<T> roots = findRoots();
        filterTree(roots, compareList);
        return roots;
    }

    /**
     * 在原节点集合中，过滤掉比对节点集合中不存在的节点
     * 
     * @param filterList
     *            原节点集合
     * @param compareList
     *            比对节点集合，包含重复数据
     */
    private void filterTree(List<T> filterList, List<T> compareList)
    {
        Iterator<T> iterator = filterList.iterator();
        while (iterator.hasNext())
        {
            boolean isRemove = true;
            T t = iterator.next();
            for (T comparePer : compareList)
            {
                if (t.getId().equals(comparePer.getId()))
                {
                    isRemove = false;
                    break;
                }
            }
            if (isRemove)
            {
                iterator.remove();
            }
            else
            {
                filterTree(t.getChildren(), compareList);
            }
        }
    }
}
