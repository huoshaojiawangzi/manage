package com.lizc.sports.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lizc.sports.common.entity.BaseEntity;
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
@EqualsAndHashCode(callSuper=true)
@Data
@Entity
@Table(name = "c_user")
public class CommonUser extends BaseEntity {

    /**
     * 登录名
     */
    @Column(nullable = false, updatable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private List<Role> roles = new ArrayList<>();

}
