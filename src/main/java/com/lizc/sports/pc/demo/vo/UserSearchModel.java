package com.lizc.sports.pc.demo.vo;


import com.lizc.sports.common.vo.BaseSearchModel;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author: lizc@sdhuijin.cn
 * @date: 2019-04-17 14:47
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class UserSearchModel extends BaseSearchModel
{
    private String name;

    private String userName;

    private String officeName;
}
