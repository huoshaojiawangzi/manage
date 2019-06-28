package com.lizc.manage.sys.entity;


import com.lizc.manage.common.entity.TreeBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 权限
 * 
 * @author lizc@sdhuijin.cn
 * @date 2019/03/06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "c_permission")
public class Permission extends TreeBaseEntity<Permission>
{

    /**
     * 权限名称
     */
    @Column(nullable = false)
    private String name;

    /**
     * 权限标签
     */
    private String tag;

    /**
     * url地址
     */
    @Column()
    private String url;

}
