/**
 * 
 */
package com.lizc.sports.sys.entity;

import com.lizc.sports.common.utils.id.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
*角色
* @author   lizc@sdhuijin.cn
* @date     2019/03/06
*/
@Data
@Entity
@Table(name = "c_role")
public class Role extends BaseEntity{

	@Column(nullable = false)
	private String name;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private List<Permission> permissions = new ArrayList<Permission>();

}
