/**
 * 
 */
package com.lizc.manage.sys.entity;


import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lizc.manage.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * 角色
 * 
 * @author lizc@sdhuijin.cn
 * @date 2019/03/06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = {"menus", "permissions"})
@Entity
@Table(name = "c_role")
public class Role extends BaseEntity
{

    @Column(nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "c_role_permission", joinColumns = {
        @JoinColumn(name = "role_id")}, inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    private List<Permission> permissions = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @OrderBy("sort ASC ")
    @JoinTable(name = "c_role_menu", joinColumns = {
        @JoinColumn(name = "role_id")}, inverseJoinColumns = {@JoinColumn(name = "menu_id")})
    private List<Menu> menus = new ArrayList<>();

    @JsonIgnore
    @JSONField(serialize = false)
    public List<Permission> getPermissions()
    {
        return this.permissions;
    }

    @JsonProperty
    public void setPermissions(List<Permission> permissions)
    {
        this.permissions = permissions;
    }

    @JsonIgnore
    @JSONField(serialize = false)
    public List<Menu> getMenus()
    {
        return this.menus;
    }

    @JsonProperty
    public void setMenus(List<Menu> menus)
    {
        this.menus = menus;
    }
}
