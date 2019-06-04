package com.lizc.sports.pc.demo.entity;


import com.lizc.sports.common.entity.BaseEntity;
import com.lizc.sports.sys.entity.CommonUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;


@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "t_user")
@Entity
public class User extends BaseEntity
{
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(nullable = false)
    private CommonUser commonUser;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(nullable = false)
    private Office office;

    private String phone;
}
