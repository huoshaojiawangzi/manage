package com.lizc.sports.common.vo;

import lombok.Data;
import org.springframework.data.domain.Sort;

/**页面查询基础模型
 * @author: lizc@sdhuijin.cn
 * @date: 2019-04-17 14:53
 **/
@Data
public class BaseSearchModel
{
    private int page;

    private int limit;

    private String sortField;

    private String order;

    private static final String DESC = "desc";

    private static final String ASC = "asc";

    public Sort getSort()
    {
        if(DESC.equals(order))
        {
            return new Sort(Sort.Direction.DESC,getSortField());
        }
        else if(ASC.equals(order))
        {
            return new Sort(Sort.Direction.ASC,getSortField());
        }
        return new Sort(Sort.Direction.DESC,"updateDate");
    }
}
