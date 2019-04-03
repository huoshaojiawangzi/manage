package com.lizc.sports.sys.entity;

import com.lizc.sports.common.utils.id.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
* 系统用户
* @author   lizc@sdhuijin.cn
* @date     2019/03/06
*/
@Data
@Entity
@Table(name = "c_user")
public class CommonUser extends BaseEntity {

	/**
	 * 登录名
	 */
	@Column(nullable = false, updatable = false, unique = true)
	private String userName;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String name;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private List<Role> roles = new ArrayList<Role>();

}
