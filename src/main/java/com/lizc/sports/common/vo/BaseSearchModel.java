package com.lizc.sports.common.vo;


import com.lizc.sports.common.entity.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;


/**
 * 页面查询基础模型
 * 
 * @author: lizc@sdhuijin.cn
 * @date: 2019-04-17 14:53
 **/
@Data
public class BaseSearchModel
{
    private int page;

    private int limit;

    private String delFlag = BaseEntity.DEL_FLAG_NORMAL;

    private ArrayList<PageSort> pageSorts;

    @Data
    private static class PageSort
    {
        private String property;

        private String order;

        private static final String DESC = "desc";

        private static final String ASC = "asc";
    }

    public Sort getSort()
    {
        List<Sort.Order> orders = new ArrayList<>();

        if(pageSorts != null&&!pageSorts.isEmpty())
        {
            for(PageSort pageSort: pageSorts)
            {
                if(StringUtils.isNotBlank(pageSort.getProperty()))
                {
                    if(PageSort.DESC.equals(pageSort.getOrder()))
                    {
                        orders.add(new Sort.Order(Sort.Direction.DESC, pageSort.getProperty()));
                    }
                    else if (PageSort.ASC.equals(pageSort.getOrder()))
                    {
                        orders.add(new Sort.Order(Sort.Direction.ASC, pageSort.getProperty()));
                    }
                }
            }
            return Sort.by(orders);
        }
        return new Sort(Sort.Direction.DESC, "updateDate");
    }
}
