/*
 * 文件名：JsonResult.java 版权：Copyright by www.sdhuijin.cn 描述： 修改人：sunp@sdhuijin.cn 修改时间：2018年6月20日
 * 修改内容：
 */

package com.lizc.sports.common.dto;


import com.lizc.sports.common.enums.SysResultCode;
import lombok.Data;


@Data
public class JsonResult<T>
{

    private Integer code;

    private String msg;

    private T result;

    public void setResultCode(SysResultCode rc)
    {
        this.code = rc.getCode();
        this.msg = rc.getMsg();
    }

}
