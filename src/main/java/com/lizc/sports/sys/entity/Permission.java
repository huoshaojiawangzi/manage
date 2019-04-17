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
 * 权限
 * 
 * @author lizc@sdhuijin.cn
 * @date 2019/03/06
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "c_permission")
@ToString(exclude = "parent")
public class Permission extends BaseEntity
{

    /**
     * 权限名称
     */
    private String name;
    /**
     * 权限标签
     */
    @Column(nullable = false)
    private String tag;

    /**
     * url地址
     */
    @Column(unique = true)
    private String url;

    /**
     * 父节点
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Permission parent;

    /**
     * 子节点
     */
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "parent", fetch = FetchType.EAGER)
    @OrderBy("createDate DESC")
    private List<Permission> children = new ArrayList<>();

}
