package com.lizc.sports.sys.entity;

import com.lizc.sports.common.utils.id.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
* 权限
* @author   lizc@sdhuijin.cn
* @date     2019/03/06
*/
@Data
@Entity
@Table(name = "c_permission")
public class Permission extends BaseEntity {

	@Column(nullable = false)
	private String name;

	private String description;

}
