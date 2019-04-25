package com.lizc.sports.common.service;


import com.lizc.sports.common.entity.BaseEntity;
import com.lizc.sports.common.entity.TreeBaseEntity;
import com.lizc.sports.common.repository.BaseRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;


/**
 * 需要分页查询的BaseService
 * 
 * @author: lizc@sdhuijin.cn
 * @date: 2019-04-22 16:32
 **/
public abstract class TreeBaseService<T extends TreeBaseEntity, ID extends Serializable, R extends BaseRepository<T, ID>> extends BaseService<T, ID, R>
{
    /**
     * 获取所有根节点
     * 
     * @return 实体类集合List
     */
    @Cacheable(value = "treeList",key = "#root.targetClass")
    public abstract List<T> findRoots();

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(T t)
    {
        super.save(t);
        if(t.getParent()!=null)
        {
            T parentOriginal =(T)t.getParent();
            T parent = super.get((ID) parentOriginal.getId());
            if(!parent.isLeaf())
            {
                parent.setLeaf(true);
                super.save(parent);
            }
        }
    }

    @Override
    public void saveAndFlush(T t)
    {
        super.saveAndFlush(t);
        if(t.getParent()!=null)
        {
            T parentOriginal =(T)t.getParent();
            T parent = super.get((ID) parentOriginal.getId());
            if(!parent.isLeaf())
            {
                parent.setLeaf(true);
                super.saveAndFlush(parent);
            }
        }
    }

    @Override
    public void delete(T t)
    {
        super.delete(t);
        if(t.getParent()!=null)
        {
            boolean leaf = false;
            T parentOriginal =(T)t.getParent();
            T parent = super.get((ID) parentOriginal.getId());
            List<T> children = parent.getChildren();
            for(T child:children)
            {
                if(child.getDelFlag().equals(BaseEntity.DEL_FLAG_NORMAL))
                {
                    leaf = true;
                    break;
                }
            }
            if(!leaf)
            {
                parent.setLeaf(true);
                super.save(parent);
            }
        }
    }

    /**
     * 获取所有根节点，并根据参数过滤掉不需要的节点
     * @param compareList 过滤list，包含所有需要的节点
     * @return 过滤后的根节点
     */
    public List<T> getfilterRoots(List<T> compareList)
    {
        List<T> roots = findRoots();
        filterRoots(roots,compareList);
        return roots;
    }

    private void filterRoots(List<T> filterList,List<T> compareList)
    {
        Iterator<T> iterator = filterList.iterator();
        while(iterator.hasNext())
        {
            boolean isRemove = true;
            for(T comparePer:compareList)
            {
                if(iterator.next().getId().equals(comparePer.getId()))
                {
                    isRemove = false;
                    break;
                }
            }
            if(isRemove)
            {
                iterator.remove();
            }
            else
            {
                filterRoots(iterator.next().getChildren(),compareList);
            }
        }
    }
}
