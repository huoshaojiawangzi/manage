package com.lizc.manage.common.controller;


import com.alibaba.fastjson.JSONObject;
import com.lizc.manage.common.dto.JsonResult;
import com.lizc.manage.common.entity.BaseEntity;
import com.lizc.manage.common.enums.SysResultCode;
import com.lizc.manage.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;


/**
 * @author: lizc
 * @date: 2019-04-10 13:30
 **/
@SuppressWarnings("ALL")
public abstract class BaseController<T extends BaseEntity, S extends BaseService>
{
    @Autowired(required = false)
    protected S service;

    @PostMapping("/save")
    public JsonResult save(@RequestBody T t)
    {
        JsonResult jsonResult = new JsonResult();
        service.save(t);
        jsonResult.setResultCode(SysResultCode.SUCCESS);
        return jsonResult;
    }

    @PostMapping("/find-all")
    public JsonResult<List<T>> findAll()
    {
        JsonResult<List<T>> jsonResult = new JsonResult<>();
        List<T> list = service.findAllEnable();
        jsonResult.setResultCode(SysResultCode.SUCCESS);
        jsonResult.setResult(list);
        return jsonResult;
    }

    @GetMapping("/delete")
    public JsonResult delete(String id)
    {
        JsonResult jsonResult = new JsonResult();
        service.delete(id);
        jsonResult.setResultCode(SysResultCode.SUCCESS);
        return jsonResult;
    }

    @PostMapping("/find-by-fileds")
    public JsonResult<List<T>> findByFileds(@RequestBody Map<String, String> map)
    {
        JsonResult<List<T>> jsonResult = new JsonResult<>();
        List<T> list = service.findByFileds(map);
        jsonResult.setResult(list);
        jsonResult.setResultCode(SysResultCode.SUCCESS);
        return jsonResult;
    }

    @RequestMapping("/find-page")
    public JsonResult<Page<T>> findPgae(@RequestBody JSONObject searchModel)
    {
        JsonResult<Page<T>> jsonResult = new JsonResult<>();
        Page<T> page = service.findPage(searchModel);
        jsonResult.setResultCode(SysResultCode.SUCCESS);
        jsonResult.setResult(page);
        return jsonResult;
    }
}
