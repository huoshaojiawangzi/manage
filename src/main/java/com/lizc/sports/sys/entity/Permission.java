package com.lizc.sports.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lizc.sports.common.utils.id.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
* 权限
* @author   lizc@sdhuijin.cn
* @date     2019/03/06
*/
@Data
@JsonIgnoreProperties(value = {"menus"})
@Entity
@Table(name = "c_permission")
public class Permission extends BaseEntity {

	@Column(nullable = false)
	private String name;

	@OneToMany(cascade = { CascadeType.ALL },mappedBy = "permission",fetch = FetchType.LAZY)
	private List<Menu> menus;

	public void addMenu(Menu menu)
	{
		if(menu.getPermission()==null)
		{
			menu.setPermission(this);
		}
		this.menus.add(menu);
	}

}
