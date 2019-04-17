package com.lizc.sports.pc.demo.entity;


import com.lizc.sports.common.entity.BaseEntity;
import com.lizc.sports.sys.entity.CommonUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;


@EqualsAndHashCode(callSuper = true)
@Table(name = "t_user")
@Entity
@Data
public class User extends BaseEntity
{
    @OneToOne(cascade = {CascadeType.ALL})
    private CommonUser commonUser;

    @ManyToOne
    private Office office;

    private String phone;
}
