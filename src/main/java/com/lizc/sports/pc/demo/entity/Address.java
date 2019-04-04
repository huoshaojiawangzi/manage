package com.lizc.sports.pc.demo.entity;

import com.lizc.sports.common.utils.id.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper=true)
@Data
@Table(name="t_address")
@Entity
public class Address extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private String name;
    
    private String phone;
}
