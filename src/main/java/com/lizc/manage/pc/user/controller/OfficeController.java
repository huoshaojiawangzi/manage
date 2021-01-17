package com.lizc.manage.pc.user.controller;


import com.lizc.manage.common.controller.TreeBaseController;
import com.lizc.manage.common.dto.JsonResult;
import com.lizc.manage.common.enums.SysResultCode;
import com.lizc.manage.common.exception.MsgException;
import com.lizc.manage.pc.user.entity.Office;
import com.lizc.manage.pc.user.service.OfficeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/home/office")
public class OfficeController extends TreeBaseController<Office, OfficeService>
{
    @Override
    public JsonResult delete(String id)
    {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setResultCode(SysResultCode.SUCCESS);
        try
        {
            service.deleteOffice(id);
        }
        catch (MsgException e)
        {
            jsonResult.setCode(1);
            jsonResult.setMsg(e.getMsg());
        }
        return jsonResult;
    }
}
