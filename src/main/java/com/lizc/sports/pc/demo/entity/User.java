package com.lizc.sports.pc.demo.entity;

import com.lizc.sports.common.entity.BaseEntity;
import com.lizc.sports.sys.entity.CommonUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper=true)
@Table(name="t_user")
@Entity
@Data
public class User extends BaseEntity
{
    @OneToOne
    private CommonUser commonUser;

    @ManyToOne
    private Office office;

    private String phone;
}
