package com.lizc.sports.common.entity;

import com.lizc.sports.sys.entity.CommonUser;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "create_by")
    @NotFound(action = NotFoundAction.IGNORE)
    @CreatedBy
    protected CommonUser createBy; // 创建者

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "update_by")
    @NotFound(action = NotFoundAction.IGNORE)
    @LastModifiedBy
    protected CommonUser updateBy; // 更新者

    @CreatedDate
    protected Date createDate;// 创建日期
    @LastModifiedDate
    protected Date updateDate;// 更新日期
    protected String delFlag = "0"; // 删除标记（0：正常；1：删除；2：审核）

}
