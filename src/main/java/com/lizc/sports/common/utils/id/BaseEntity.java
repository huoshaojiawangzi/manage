package com.lizc.sports.common.utils.id;

import com.lizc.sports.sys.entity.CommonUser;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Data
public abstract class BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    private String id;

    protected String remarks; // 备注
    protected CommonUser createBy; // 创建者
    protected Date createDate;// 创建日期
    protected CommonUser updateBy; // 更新者
    protected Date updateDate;// 更新日期
    protected String delFlag; // 删除标记（0：正常；1：删除；2：审核）

}
