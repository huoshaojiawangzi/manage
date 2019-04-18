package com.lizc.sports.common.controller;


import com.lizc.sports.common.dto.JsonResult;
import com.lizc.sports.common.entity.BaseEntity;
import com.lizc.sports.common.enums.SysResultCode;
import com.lizc.sports.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * @author: lizc@sdhuijin.cn
 * @date: 2019-04-10 13:30
 **/
@SuppressWarnings("ALL")
public abstract class BaseController<T extends BaseEntity,S extends BaseService>
{
    @Autowired(required = false)
    private S service;

    @RequestMapping("/save")
    public JsonResult save(T t)
    {
        JsonResult jsonResult = new JsonResult();
        service.save(t);
        jsonResult.setResultCode(SysResultCode.SUCCESS);
        return jsonResult;
    }

/*    public JsonResult<Page<T>> findPgae(UserSearchModel searchModel)
    {
        JsonResult<Page<T>> jsonResult = new JsonResult<>();
        Page<T> page = service.findPage(searchModel);
        jsonResult.setResultCode(SysResultCode.SUCCESS);
        jsonResult.setResult(page);
        return jsonResult;
    }*/

    @RequestMapping("/find-all")
    public JsonResult<List<T>> findAll()
    {
        JsonResult<List<T>> jsonResult = new JsonResult<>();
        List<T> t = service.findAll();
        jsonResult.setResultCode(SysResultCode.SUCCESS);
        jsonResult.setResult(t);
        return jsonResult;
    }

    @RequestMapping("/delete")
    public JsonResult delete(String id)
    {
        JsonResult jsonResult = new JsonResult();
        service.delete(id);
        jsonResult.setResultCode(SysResultCode.SUCCESS);
        return jsonResult;
    }
}
