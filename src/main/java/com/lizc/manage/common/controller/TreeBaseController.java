package com.lizc.manage.common.controller;


import com.lizc.manage.common.dto.JsonResult;
import com.lizc.manage.common.entity.TreeBaseEntity;
import com.lizc.manage.common.enums.SysResultCode;
import com.lizc.manage.common.service.TreeBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


/**
 * @author: lizc
 * @date: 2019-04-10 13:30
 **/
@SuppressWarnings("ALL")
public abstract class TreeBaseController<T extends TreeBaseEntity, S extends TreeBaseService> extends BaseController<T, S>
{
    @Autowired(required = false)
    protected S service;

    @PostMapping("/find-roots")
    public JsonResult<List<T>> findRoots()
    {
        JsonResult jsonResult = new JsonResult();
        List<T> roots = service.findRoots();
        jsonResult.setResultCode(SysResultCode.SUCCESS);
        jsonResult.setResult(roots);
        return jsonResult;
    }
}
