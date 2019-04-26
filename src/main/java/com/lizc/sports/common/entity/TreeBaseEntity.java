package com.lizc.sports.common.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: lizc@sdhuijin.cn
 * @date: 2019-04-25 14:11
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
@ToString(exclude = "parent")
public abstract class TreeBaseEntity<T> extends  BaseEntity
{

    /**
     * 每个类型中本标签的排序位置
     */
    private int sort;

    /**
     * 是否还有子节点
     */
    private boolean leaf = false;

    /**
     * 父节点
     */
    @JSONField(serialize = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private T parent;

    /**
     * 子节点
     */
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "parent", fetch = FetchType.EAGER)
    @OrderBy("sort ASC ")
    @Fetch(FetchMode.SUBSELECT)
    private List<T> children = new ArrayList<>();

}