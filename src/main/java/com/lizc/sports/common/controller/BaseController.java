package com.lizc.sports.common.controller;


import com.lizc.sports.common.dto.JsonResult;
import com.lizc.sports.common.entity.BaseEntity;
import com.lizc.sports.common.enums.SysResultCode;
import com.lizc.sports.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;


/**
 * @author: lizc@sdhuijin.cn
 * @date: 2019-04-10 13:30
 **/
@SuppressWarnings("ALL")
public abstract class BaseController<T extends BaseEntity, S extends BaseService>
{
    @Autowired(required = false)
    protected S service;

    @RequestMapping("/save")
    public JsonResult save(@RequestBody T t)
    {
        JsonResult jsonResult = new JsonResult();
        service.save(t);
        jsonResult.setResultCode(SysResultCode.SUCCESS);
        return jsonResult;
    }

    @RequestMapping("/find-all")
    public JsonResult<List<T>> findAll()
    {
        JsonResult<List<T>> jsonResult = new JsonResult<>();
        List<T> list = service.findAllEnable();
        jsonResult.setResultCode(SysResultCode.SUCCESS);
        jsonResult.setResult(list);
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

    @PostMapping("/find-by-filed")
    public JsonResult<List<T>> findByFileds(@RequestBody Map<String,String> map)
    {
        JsonResult<List<T>> jsonResult = new JsonResult<>();
        List<T> list = service.findByFileds(map);
        jsonResult.setResult(list);
        jsonResult.setResultCode(SysResultCode.SUCCESS);
        return jsonResult;
    }
}
