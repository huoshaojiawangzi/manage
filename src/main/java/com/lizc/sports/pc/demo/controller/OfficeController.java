package com.lizc.sports.pc.demo.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lizc.sports.common.controller.TreeBaseController;
import com.lizc.sports.common.dto.JsonResult;
import com.lizc.sports.common.enums.SysResultCode;
import com.lizc.sports.common.exception.MsgException;
import com.lizc.sports.pc.demo.entity.Office;
import com.lizc.sports.pc.demo.service.OfficeService;


@RestController
@RequestMapping("/home/office")
public class OfficeController extends TreeBaseController<Office, OfficeService>
{
    @Override
    public JsonResult delete(String id)
    {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setResultCode(SysResultCode.SUCCESS);
        try {
            service.deleteOffice(id);
        } catch (MsgException e) {
            jsonResult.setCode(1);
            jsonResult.setMsg(e.getMsg());
        }
        return jsonResult;
    }
}
