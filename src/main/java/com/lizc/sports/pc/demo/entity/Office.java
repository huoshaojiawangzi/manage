package com.lizc.sports.pc.demo.entity;


import com.alibaba.fastjson.annotation.JSONField;
import com.lizc.sports.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author: lizc@sdhuijin.cn
 * @date: 2019-04-11 14:46
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "t_office")
@Entity
public class Office extends BaseEntity
{
    private String name;

    private String address;

    private String phone;

    @OneToOne
    private User manager;

    /**
     * 父节点
     */
    @JSONField(serialize = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Office parent;

    /**
     * 子节点
     */
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "parent", fetch = FetchType.EAGER)
    @OrderBy("createDate desc ")
    @Fetch(FetchMode.SUBSELECT)
    private List<Office> children = new ArrayList<>();
}
