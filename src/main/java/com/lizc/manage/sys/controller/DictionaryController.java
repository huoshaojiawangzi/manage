package com.lizc.manage.sys.controller;


import com.lizc.manage.common.controller.BaseController;
import com.lizc.manage.common.dto.JsonResult;
import com.lizc.manage.common.enums.SysResultCode;
import com.lizc.manage.sys.entity.Dictionary;
import com.lizc.manage.sys.sevice.DictionaryService;
import com.lizc.manage.sys.utils.DictUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author: lizc
 * @date: 2019-04-23 11:55
 **/
@RestController
@RequestMapping("/home/dictionary")
public class DictionaryController extends BaseController<Dictionary, DictionaryService>
{
    @Override
    public JsonResult<List<Dictionary>> findAll()
    {
        JsonResult<List<Dictionary>> jsonResult = new JsonResult<>();
        List<Dictionary> list = DictUtils.findAll();
        jsonResult.setResultCode(SysResultCode.SUCCESS);
        jsonResult.setResult(list);
        return jsonResult;
    }
}
