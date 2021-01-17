package com.lizc.manage.common.enums;

public enum SysResultCode implements IResultCode {

    /**
     * 返回 code:0，msg:成功;
     */
    SUCCESS(0, " 操作成功"), FAILURE(1, "操作失败"), UNLOGIN(2, "尚未登录,请先登录系统"), UNAUTH(3, "您没有该访问权限");

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
