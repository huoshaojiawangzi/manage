package com.lizc.sports.pc.demo.entity;


import com.lizc.sports.common.entity.TreeBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * @author: lizc@sdhuijin.cn
 * @date: 2019-04-11 14:46
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "t_office")
@Entity
public class Office extends TreeBaseEntity<Office>
{
    private String name;

    private String address;

    private String phone;

    private String manager;
}
