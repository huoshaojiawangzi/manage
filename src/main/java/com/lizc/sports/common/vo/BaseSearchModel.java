package com.lizc.sports.common.vo;


import com.lizc.sports.common.entity.BaseEntity;
import lombok.Data;
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

    private ArrayList<LoalSort> loalSorts;

    @Data
    private static class LoalSort
    {
        private String property;

        private String order;

        private static final String DESC = "desc";

        private static final String ASC = "asc";
    }

    public Sort getSort()
    {
        List<Sort.Order> orders = new ArrayList<>();

        if(loalSorts != null&&!loalSorts.isEmpty())
        {
            for(LoalSort loalSort:loalSorts)
            {
                if(LoalSort.DESC.equals(loalSort.getOrder()))
                {
                    orders.add(new Sort.Order(Sort.Direction.DESC, loalSort.getProperty()));
                }
                else if (LoalSort.ASC.equals(loalSort.getOrder()))
                {
                    orders.add(new Sort.Order(Sort.Direction.ASC, loalSort.getProperty()));
                }
            }
            return Sort.by(orders);
        }
        return new Sort(Sort.Direction.DESC, "updateDate");
    }
}
