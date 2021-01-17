package com.lizc.manage.pc.user.vo;


import com.lizc.manage.common.vo.BaseSearchModel;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author: lizc
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
