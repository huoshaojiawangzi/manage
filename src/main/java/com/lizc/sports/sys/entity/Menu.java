package com.lizc.sports.sys.entity;


import com.lizc.sports.common.entity.TreeBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 菜单
 * 
 * @author lizc@sdhuijin.cn
 * @date 2019/03/06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "c_menu")
public class Menu extends TreeBaseEntity<Menu>
{

    /**
     * 菜单名称
     */
    @Column(nullable = false, unique = true)
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

}
