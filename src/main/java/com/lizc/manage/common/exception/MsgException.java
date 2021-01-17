package com.lizc.manage.common.exception;

/**
 * 用来保存操作失败信息的异常
 * 
 * @author: lizc
 * @date: 2019-06-20 13:53
 **/
public class MsgException extends Exception
{
    private static final long serialVersionUID = 1L;

    private String msg;

    public MsgException()
    {
        super();
    }

    public MsgException(String message)
    {
        super(message);
        this.msg = message;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }
}
