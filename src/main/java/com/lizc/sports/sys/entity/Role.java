/**
 * 
 */
package com.lizc.sports.sys.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lizc.sports.common.entity.BaseEntity;
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
@ToString(exclude = {"menus","permissions"})
@Entity
@Table(name = "c_role")
public class Role extends BaseEntity
{

    @Column(nullable = false, unique = true)
    private String name;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "c_role_permission", joinColumns = {
        @JoinColumn(name = "role_id")}, inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    private List<Permission> permissions = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @OrderBy("sort ASC ")
    @JoinTable(name = "c_role_menu", joinColumns = {
        @JoinColumn(name = "role_id")}, inverseJoinColumns = {@JoinColumn(name = "menu_id")})
    private List<Menu> menus = new ArrayList<>();
}
