package com.lizc.manage.sys.controller;


import com.lizc.manage.common.controller.BaseController;
import com.lizc.manage.common.dto.JsonResult;
import com.lizc.manage.common.enums.SysResultCode;
import com.lizc.manage.sys.bo.RoleCache;
import com.lizc.manage.sys.entity.Role;
import com.lizc.manage.sys.sevice.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author: lizc
 * @date: 2019-04-16 13:55
 **/
@RestController
@RequestMapping("/home/role")
public class RoleController extends BaseController<Role, RoleService>
{
    @GetMapping("/get")
    public JsonResult<RoleCache> getById(String id)
    {
        JsonResult<RoleCache> jsonResult = new JsonResult<>();
        jsonResult.setResult(new RoleCache().generate(service.getComplete(id)));
        jsonResult.setResultCode(SysResultCode.SUCCESS);
        return jsonResult;
    }
}
