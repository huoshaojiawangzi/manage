package com.lizc.sports.common.adivce;


import com.lizc.sports.common.exception.UserOverdueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lizc.sports.common.dto.JsonResult;
import com.lizc.sports.common.enums.SysResultCode;


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


    private JsonResult<String> getResult(String msg)
    {
        JsonResult<String> jsonResult = new JsonResult<>();
        jsonResult.setResultCode(SysResultCode.FAILURE);
        jsonResult.setResult(msg);
        return jsonResult;
    }

    private JsonResult<String> getInfoResult(String msg,Exception e)
    {
        logger.info(msg, e.getMessage());
        return getResult(msg);
    }

    private JsonResult<String> getWarnResult(String msg,Exception e)
    {
        logger.warn(msg, e);
        return getResult(msg);
    }

    private JsonResult<String> getErrorResult(String msg,Exception e)
    {
        logger.error(msg, e);
        return getResult(msg);
    }

    /**
     * 未知异常处理
     * @param e Exception
     * @return json数据
     */
    @ExceptionHandler(Exception.class)
    public JsonResult<String> exceptionHandler(Exception e)
    {
        return getErrorResult(UNKNOWN_EXCEPTION,e);
    }

    /**
     * 用户身份过期异常处理
     * @param e 自定义异常，用户身份过期
     * @return json数据
     */
    @ExceptionHandler(UserOverdueException.class)
    public JsonResult<String> userOverdueExceptionHandler(UserOverdueException e)
    {
        return getInfoResult(USER_OVERDUE,e);
    }
}
