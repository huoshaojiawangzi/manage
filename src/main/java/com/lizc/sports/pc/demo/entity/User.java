package com.lizc.sports.pc.demo.entity;

import com.lizc.sports.common.utils.id.IdEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@EqualsAndHashCode(callSuper=true)
@Table(name="t_user")
@Entity
@Data
public class User extends IdEntity
{
    private static final long serialVersionUID = 1L;

    private String password;
    
    private String authority;
    
    private String userName;

    private Date date;

}
