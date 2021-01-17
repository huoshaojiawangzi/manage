package com.lizc.manage.common.controller;


import com.lizc.manage.common.dto.JsonResult;
import com.lizc.manage.common.entity.BaseEntity;
import com.lizc.manage.common.enums.SysResultCode;
import com.lizc.manage.common.service.PageableBaseService;
import com.lizc.manage.common.vo.BaseSearchModel;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 需要分页查询的BaseController
 * 
 * @author: lizc
 * @date: 2019-04-22 16:38
 **/
@SuppressWarnings("ALL")
public class PageableBaseController<T extends BaseEntity, M extends BaseSearchModel, S extends PageableBaseService> extends BaseController<T, S>
{

    @RequestMapping("/find-page")
    public JsonResult<Page<T>> findPgae(@RequestBody M searchModel)
    {
        JsonResult<Page<T>> jsonResult = new JsonResult<>();
        Page<T> page = service.findPage(searchModel);
        jsonResult.setResultCode(SysResultCode.SUCCESS);
        jsonResult.setResult(page);
        return jsonResult;
    }
}
