package com.lizc.sports.common.controller;


import com.lizc.sports.common.dto.JsonResult;
import com.lizc.sports.common.entity.BaseEntity;
import com.lizc.sports.common.enums.SysResultCode;
import com.lizc.sports.common.service.TreeBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * @author: lizc@sdhuijin.cn
 * @date: 2019-04-10 13:30
 **/
@SuppressWarnings("ALL")
public abstract class TreeBaseController<T extends BaseEntity, S extends TreeBaseService> extends BaseController<T,S>
{
    @Autowired(required = false)
    protected S service;

    @RequestMapping("/find-roots")
    public JsonResult<List<T>> findRoots()
    {
        JsonResult jsonResult = new JsonResult();
        List<T> roots = service.findRoots();
        jsonResult.setResultCode(SysResultCode.SUCCESS);
        jsonResult.setResult(roots);
        return jsonResult;
    }
}
