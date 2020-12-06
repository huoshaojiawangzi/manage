package com.lizc.manage.common.service;


import com.alibaba.fastjson.JSONObject;
import com.lizc.manage.common.entity.BaseEntity;
import com.lizc.manage.common.repository.BaseRepository;
import com.lizc.manage.common.utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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

    protected Class<T> clazz;

    public BaseService()
    {
        this.clazz = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * 删除allEnableList以及allList缓存
     */
    private void delRedis()
    {
        RedisUtils.del("allEnableList:" + clazz.toString());
        RedisUtils.del("allList:" + clazz.toString());
    }

    /**
     * 刷新数据流
     */
    public void flush()
    {
        repostitory.flush();
    }

    /**
     * 包含懒加载的对象
     * 
     * @param id
     * @return
     */
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
        delRedis();
    }

    public void saveAndFlush(T t)
    {
        repostitory.saveAndFlush(t);
        delRedis();
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
        save(t);
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
     */
    public void forceDelete(T t)
    {
        repostitory.delete(t);
        delRedis();
    }

    public T findOne(JSONObject searchModel)
    {
        return repostitory.findOne(getSpec(searchModel)).orElse(null);
    }

    /**
     * 根据字段实体属性名称，以及属性值获取实体列表<p>
     * 目前最多支持两级查询，即最多支持实体的属性以及其包含对象的属性查询
     *
     * @param map key表示属性名，value表示属性值
     * @return list:实体列表
     */
    public List<T> findByFileds(Map<String, Object> map) {
        Specification specification = (Specification<T>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            predicateList.add(
                    criteriaBuilder.equal(root.get("delFlag"), T.DEL_FLAG_NORMAL));
            for (String key : map.keySet()) {
                if (map.get(key) instanceof Map) {
                    Map<String, Object> itemMap = (Map<String, Object>) map.get(key);
                    for (String itemKey : itemMap.keySet()) {
                        predicateList.add(criteriaBuilder.equal(root.get(key).get(itemKey),
                                "".equals(itemMap.get(itemKey)) ? null : itemMap.get(itemKey)));
                    }
                } else {
                    predicateList.add(criteriaBuilder.equal(root.get(key),
                            "".equals(map.get(key)) ? null : map.get(key)));
                }
            }
            Predicate[] predicates = new Predicate[predicateList.size()];
            return criteriaBuilder.and(predicateList.toArray(predicates));
        };
        return repostitory.findAll(specification);
    }

    /**
     * 找到所有实体(包含已删除的)
     */
    public List<T> findAll()
    {
        List<T> all = RedisUtils.getList("allList:" + clazz.toString(), clazz);
        if (all == null)
        {
            all = repostitory.findAll();
            RedisUtils.setList("allList:" + clazz.toString(), all);
        }
        return all;
    }

    /**
     * 根据example找到所有实体,通过对象作为查询条件不建议使用该方法
     */
    public List<T> findAll(Example<T> example)
    {
        return repostitory.findAll(example);
    }

    /**
     * 根据specification找到所有实体
     */
    public List<T> findAll(JSONObject searchModel)
    {
        return repostitory.findAll(getSpec(searchModel));
    };

    /**
     * 找到所有实体(不包含已删除的)
     * 
     * @return
     */
    public List<T> findAllEnable()
    {
        List<T> allEnable = RedisUtils.getList("allEnableList:" + clazz.toString(), clazz);
        if (allEnable == null)
        {
            Specification<T> specification = (Specification<T>)(root, query,
                                                                criteriaBuilder) -> criteriaBuilder.equal(
                                                                    root.<String> get("delFlag"),
                                                                    BaseEntity.DEL_FLAG_NORMAL);
            allEnable = repostitory.findAll(specification);
            RedisUtils.setList("allEnableList:" + clazz.toString(), allEnable);
        }
        return allEnable;
    }

    /**
     * 如果该id实体存在，返回true，否则返回false
     */
    public boolean exists(ID id)
    {
        return repostitory.existsById(id);
    }

    /**
     * 获取分页
     *
     * @param searchModel
     *            查询模型
     * @return Page
     */
    @Transactional
    public Page<T> findPage(JSONObject searchModel)
    {
        Integer page = searchModel.getInteger("page");
        Integer limit = searchModel.getInteger("limit");
        if (page == null) {
            page = 0;
        } else {
            page--;
        }
        if (limit == null) {
            limit = 20;
        }
        return repostitory.findAll(getSpec(searchModel),
                PageRequest.of(page, limit, getSort(searchModel)));
    }

    /**
     * 根据model中的查询条件获取spec
     *
     * @param searchModel
     *            查询模型
     * @return Specification
     */
    protected Specification<T> getSpec(JSONObject searchModel)
    {
        return (Specification<T>)(root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            setPredicates(root, criteriaBuilder, predicateList, searchModel);
            Predicate[] predicates = new Predicate[predicateList.size()];
            return criteriaBuilder.and(predicateList.toArray(predicates));
        };
    }

    protected Sort getSort(JSONObject searchModel) {
        List<Sort.Order> orders = new ArrayList<>();
        String order = searchModel.getString("order");
        String column = searchModel.getString("column");
        if (order != null && order.equals("ascending")) {
            orders.add(new Sort.Order(Sort.Direction.ASC, column));
        } else if (order != null && order.equals("descending")) {
            orders.add(new Sort.Order(Sort.Direction.DESC, column));
        }
        orders.add(new Sort.Order(Sort.Direction.DESC, "updateDate"));
        return Sort.by(orders);
    }

    /**
     * 给搜索增加查询条件
     *
     * @param root
     *            元模型
     * @param criteriaBuilder
     *            查询条件构建器
     * @param predicates
     *            predicate的list稽核
     * @param searchModel
     *            查询模型
     */
    protected void setPredicates(Root<T> root, CriteriaBuilder criteriaBuilder,
                                          List<Predicate> predicates, JSONObject searchModel) {
        predicates.add(
                criteriaBuilder.equal(root.<String> get("delFlag"),BaseEntity.DEL_FLAG_NORMAL));
    }
}
