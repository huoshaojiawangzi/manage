package com.lizc.manage.sys.entity;


import com.lizc.manage.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 字典
 * 
 * @author lizc@sdhuijin.cn
 * @date 2019/03/06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "c_dictionary")
public class Dictionary extends BaseEntity
{

    /**
     * 每个类型中本标签的排序位置
     */
    private int sort;

    /**
     * 类型
     */
    @Column(nullable = false)
    private String type;// 比如贷款类型

    /**
     * 标签
     */
    @Column(nullable = false)
    private String label;// 比如分为表内/表外

    /**
     * 实际值
     */
    @Column(nullable = false)
    private String value;// 表内外对应真实的值，比如1、2

}
