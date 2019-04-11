package com.lizc.sports.sys.entity;

import com.lizc.sports.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
* 菜单
* @author   lizc@sdhuijin.cn
* @date     2019/03/06
*/
@EqualsAndHashCode(callSuper=true)
@Data
@Entity
@Table(name = "c_menu")
public class Menu extends BaseEntity {

	@Column(nullable = false)
	private String name;

	/**
	 * 前端路由地址
	 */
	@Column(unique = true)
	private String path;

	/**
	 * 是否隐藏
	 */
	private boolean hidden = false;

	/**
	 *是否还有子节点
	 */
	private boolean leaf = true;

	/**
	 * 父节点
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private Menu parent;

	/**
	 * 子节点
	 */
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "parent", fetch = FetchType.EAGER)
	@OrderBy("createDate DESC")
	private List<Menu> children = new ArrayList<>();

}
