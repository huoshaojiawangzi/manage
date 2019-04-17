package com.lizc.sports.pc.demo.entity;


import com.lizc.sports.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * @author: lizc@sdhuijin.cn
 * @date: 2019-04-11 14:46
 **/
@EqualsAndHashCode(callSuper = true)
@Table(name = "t_office")
@Entity
@Data
public class Office extends BaseEntity
{
    private String name;

    private String address;
}
