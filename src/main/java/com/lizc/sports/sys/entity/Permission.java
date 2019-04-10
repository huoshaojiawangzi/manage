package com.lizc.sports.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lizc.sports.common.entity.BaseEntity;
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
@JsonIgnoreProperties(value = {"menus"})
@Entity
@Table(name = "c_permission")
public class Permission extends BaseEntity {

	@Column(nullable = false)
	private String name;

	/**
	 * url地址
	 */
	private String url;

	/**
	 * 父节点
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private Permission parent;

	/**
	 * 子节点
	 */
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "parent", fetch = FetchType.EAGER)
	private List<Permission> children = new ArrayList<>();

}
