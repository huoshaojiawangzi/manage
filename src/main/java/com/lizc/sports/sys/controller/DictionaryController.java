package com.lizc.sports.sys.controller;

import com.lizc.sports.common.controller.PageableBaseController;
import com.lizc.sports.common.dto.JsonResult;
import com.lizc.sports.common.enums.SysResultCode;
import com.lizc.sports.sys.entity.Dictionary;
import com.lizc.sports.sys.sevice.DictionaryService;
import com.lizc.sports.sys.utils.DictUtils;
import com.lizc.sports.sys.vo.DictionarySearchModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: lizc@sdhuijin.cn
 * @date: 2019-04-23 11:55
 **/
@RestController
@RequestMapping("home/dictionary")
public class DictionaryController extends PageableBaseController<Dictionary, DictionarySearchModel, DictionaryService>
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
