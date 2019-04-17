/*
 * 文件名：ResultCode.java 版权：Copyright by www.sdhuijin.cn 描述： 修改人：sunp@sdhuijin.cn 修改时间：2018年6月20日
 * 修改内容：
 */

package com.lizc.sports.common.enums;

public enum SysResultCode implements IResultCode {

    /**
     * 返回 code:0，msg:成功;
     */
    SUCCESS(0, " 操作成功"), FAILURE(1, "操作失败");

    private Integer code;

    private String msg;

    SysResultCode(Integer code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString()
    {
        return this.name();
    }

    @Override
    public Integer getCode()
    {
        return this.code;
    }

    @Override
    public String getMsg()
    {
        return this.msg;
    }

}
