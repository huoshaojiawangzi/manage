package com.lizc.manage.common.exception;

/**
 * 用户信息过期异常
 * 
 * @author: lizc@sdhuijin.cn
 * @date: 2019-05-20 13:53
 **/
public class UserOverdueException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public UserOverdueException()
    {
        super();
    }

    public UserOverdueException(String message)
    {
        super(message);
    }

    public UserOverdueException(Throwable cause)
    {
        super(cause);
    }

    public UserOverdueException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
