package com.lizc.manage.sys.vo;


import com.lizc.manage.common.vo.BaseSearchModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author: lizc
 * @date: 2019-06-25 10:39
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class DictionarySearchModel extends BaseSearchModel
{
    /**
     * 类型
     */
    private String type;

    /**
     * 标签
     */
    private String label;

    /**
     * 描述
     */
    private String remarks;
}
