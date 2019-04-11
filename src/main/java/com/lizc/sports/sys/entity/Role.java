/**
 * 
 */
package com.lizc.sports.sys.entity;

import com.lizc.sports.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
*角色
* @author   lizc@sdhuijin.cn
* @date     2019/03/06
*/
@EqualsAndHashCode(callSuper=true)
@Data
@Entity
@Table(name = "c_role")
public class Role extends BaseEntity{

	@Column(nullable = false,unique = true)
	private String name;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private List<Permission> permissions = new ArrayList<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinColumn(name="role_id")
	private List<Menu> menus = new ArrayList<>();
}
