package com.lizc.sports.common.entity;


import com.alibaba.fastjson.annotation.JSONField;
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
import java.time.LocalDateTime;


@Data
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    private String id;

    protected String remarks; // 备注

    @JSONField(serialize = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "create_by")
    @NotFound(action = NotFoundAction.IGNORE)
    @CreatedBy
    protected CommonUser createBy; // 创建者

    @JSONField(serialize = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "update_by")
    @NotFound(action = NotFoundAction.IGNORE)
    @LastModifiedBy
    protected CommonUser updateBy; // 更新者

    @CreatedDate
    protected LocalDateTime createDate;// 创建日期

    @LastModifiedDate
    protected LocalDateTime updateDate;// 更新日期

    protected String delFlag = DEL_FLAG_NORMAL; // 删除标记（0：正常；1：删除）

    /**
     * 删除标记:正常
     */
    public static final String DEL_FLAG_NORMAL = "0";

    /**
     * 删除标记:删除
     */
    public static final String DEL_FLAG_DELETE = "1";

}
