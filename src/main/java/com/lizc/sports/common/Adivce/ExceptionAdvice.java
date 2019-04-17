package com.lizc.sports.common.Adivce;

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
    private Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(Exception.class)
    public JsonResult<String> exceptionHandler(Exception e)
    {
        JsonResult<String> jsonResult = new JsonResult<>();
        jsonResult.setResultCode(SysResultCode.FAILURE);
        jsonResult.setResult("出现未知异常");
        logger.error("未知异常：",e);
        return jsonResult;
    }
}
