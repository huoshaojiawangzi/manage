package com.lizc.sports.sys.entity;

import com.lizc.sports.common.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;

/**
* 字典
* @author   lizc@sdhuijin.cn
* @date     2019/03/06
*/
@Data
@Entity
@Table(name = "c_dictionary")
public class Dictionary extends BaseEntity {

	/**
	 * 类型
	 */
	@Column(nullable = false)
	private String type;//比如贷款类型

	/**
	 * 名称
	 */
	@Column(nullable = false)
	private String name;//比如分为表内/表外

	/**
	 * 实际值
	 */
	@Column(nullable = false)
	private String value;//表内外对应真实的值，比如1、2

}
