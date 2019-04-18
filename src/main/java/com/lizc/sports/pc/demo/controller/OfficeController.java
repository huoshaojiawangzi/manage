package com.lizc.sports.pc.demo.controller;


import com.lizc.sports.common.controller.BaseController;
import com.lizc.sports.common.dto.JsonResult;
import com.lizc.sports.common.enums.SysResultCode;
import com.lizc.sports.pc.demo.entity.Office;
import com.lizc.sports.pc.demo.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("home/office")
public class OfficeController extends BaseController<Office,OfficeService>
{
    private final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @SuppressWarnings("Duplicates")
    @RequestMapping("/find-roots")
    public JsonResult<List<Office>> findRoots()
    {
        JsonResult<List<Office>> jsonResult = new JsonResult<>();
        List<Office> offices = officeService.findRoots();
        jsonResult.setResultCode(SysResultCode.SUCCESS);
        jsonResult.setResult(offices);
        return jsonResult;
    }
}
