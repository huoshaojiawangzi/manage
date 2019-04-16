package com.lizc.sports.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lizc.sports.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
* 权限
* @author   lizc@sdhuijin.cn
* @date     2019/03/06
*/
@EqualsAndHashCode(callSuper=true)
@Data
@Entity
@Table(name = "c_permission")
public class Permission extends BaseEntity {

	@Column(nullable = false)
	private String name;

	/**
	 * url地址
	 */
	@Column(unique = true)
	private String url;

	/**
	 * 父节点
	 */
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Permission parent;

	/**
	 * 子节点
	 */
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "parent", fetch = FetchType.EAGER)
	private List<Permission> children = new ArrayList<>();

}
