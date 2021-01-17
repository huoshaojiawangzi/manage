package com.lizc.manage.pc.user.entity;


import com.lizc.manage.common.entity.TreeBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * @author: lizc
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

    /**
     * 机构负责人姓名
     */
    private String managerName;

    /**
     * 机构编号
     */
    private String code;
}
