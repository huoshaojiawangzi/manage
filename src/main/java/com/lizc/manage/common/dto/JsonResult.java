package com.lizc.manage.common.dto;


import com.lizc.manage.common.enums.SysResultCode;
import lombok.Data;


@Data
public class JsonResult<T>
{

    private Integer code;

    private String msg;

    private T result;

    public JsonResult()
    {}

    public JsonResult(SysResultCode rc)
    {
        this.code = rc.getCode();
        this.msg = rc.getMsg();
    }

    public void setResultCode(SysResultCode rc)
    {
        this.code = rc.getCode();
        this.msg = rc.getMsg();
    }

}
