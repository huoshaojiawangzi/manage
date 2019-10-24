package com.lizc.manage.common.adivce;


import com.lizc.manage.common.dto.JsonResult;
import com.lizc.manage.common.enums.SysResultCode;
import com.lizc.manage.common.exception.UserOverdueException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @author: lizc@sdhuijin.cn
 * @date: 2019-04-17 14:06
 **/
@RestControllerAdvice
public class ExceptionAdvice
{
    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    private static final String UNKNOWN_EXCEPTION = "未知异常";

    private static final String USER_OVERDUE = "用户身份过期";

    private static final String USER_UNAUTH = "用户访问权限不足";

    private JsonResult<String> getResult(String msg, SysResultCode sysResultCode)
    {
        JsonResult<String> jsonResult = new JsonResult<>(sysResultCode);
        jsonResult.setResult(msg);
        return jsonResult;
    }

    private JsonResult<String> getInfoResult(String msg, Exception e, SysResultCode sysResultCode)
    {
        logger.info(msg, e.getMessage());
        return getResult(msg, sysResultCode);
    }

    private JsonResult<String> getWarnResult(String msg, Exception e, SysResultCode sysResultCode)
    {
        logger.warn(msg, e);
        return getResult(msg, sysResultCode);
    }

    private JsonResult<String> getErrorResult(String msg, Exception e, SysResultCode sysResultCode)
    {
        logger.error(msg, e);
        return getResult(msg, sysResultCode);
    }

    /**
     * 未知异常处理
     * 
     * @param e
     *            Exception
     * @return json数据
     */
    @ExceptionHandler(Exception.class)
    public JsonResult<String> exceptionHandler(Exception e)
    {
        return getErrorResult(UNKNOWN_EXCEPTION, e, SysResultCode.FAILURE);
    }

    /**
     * 用户身份过期异常处理
     * 
     * @param e
     *            自定义异常，用户身份过期
     * @return json数据
     */
    @ExceptionHandler(UserOverdueException.class)
    public JsonResult<String> userOverdueExceptionHandler(UserOverdueException e)
    {
        return getInfoResult(USER_OVERDUE, e, SysResultCode.FAILURE);
    }

    /**
     * 用户访问权限不足异常
     * 
     * @param e
     * @return
     */
    @ExceptionHandler(value = UnauthorizedException.class)
    public JsonResult AuthcErrorHandler(UnauthorizedException e)
    {
        return getInfoResult(USER_UNAUTH, e, SysResultCode.UNAUTH);
    }
}
