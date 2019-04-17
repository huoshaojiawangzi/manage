package com.lizc.sports.common.vo;

import lombok.Data;

/**页面查询基础模型
 * @author: lizc@sdhuijin.cn
 * @date: 2019-04-17 14:53
 **/
@Data
public class BaseSearchModel
{
    protected int page;

    protected int limit;
}
