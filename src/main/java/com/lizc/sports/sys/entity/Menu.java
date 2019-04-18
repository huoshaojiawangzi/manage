package com.lizc.sports.sys.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lizc.sports.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * 菜单
 * 
 * @author lizc@sdhuijin.cn
 * @date 2019/03/06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "parent")
@Entity
@Table(name = "c_menu")
public class Menu extends BaseEntity
{

    /**
     * 每个类型中本标签的排序位置
     */
    @Column(nullable = false)
    private int sort;

    @Column(nullable = false,unique = true)
    private String name;

    /**
     * 前端路由地址
     */
    @Column(unique = true)
    private String path;

    /**
     * 是否隐藏
     */
    private boolean hidden = false;

    /**
     * 是否还有子节点
     */
    private boolean leaf = true;

    /**
     * 父节点
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Menu parent;

    /**
     * 子节点
     */
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "parent", fetch = FetchType.EAGER)
    @OrderBy("sort ASC ")
    private List<Menu> children = new ArrayList<>();

}
