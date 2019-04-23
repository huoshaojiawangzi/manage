package com.lizc.sports.common.controller;


import com.lizc.sports.common.dto.JsonResult;
import com.lizc.sports.common.entity.BaseEntity;
import com.lizc.sports.common.enums.SysResultCode;
import com.lizc.sports.common.service.PageableBaseService;
import com.lizc.sports.common.vo.BaseSearchModel;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 需要分页查询的BaseController
 * 
 * @author: lizc@sdhuijin.cn
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
