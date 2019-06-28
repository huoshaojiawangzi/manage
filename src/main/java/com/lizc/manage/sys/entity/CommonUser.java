package com.lizc.manage.sys.entity;


import com.lizc.manage.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * 系统用户
 *
 * @author lizc@sdhuijin.cn
 * @date 2019/03/06
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "c_user")
public class CommonUser extends BaseEntity
{

    @Column(nullable = false, updatable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    /**
     * 该用户当前角色的位置,切换角色时改变
     */
    private int roleIndex = 0;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "c_user_role", joinColumns = {
        @JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roles = new ArrayList<>();

}
