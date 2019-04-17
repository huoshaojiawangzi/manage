package com.lizc.sports.sys.controller;


import com.lizc.sports.common.controller.BaseController;
import com.lizc.sports.common.dto.JsonResult;
import com.lizc.sports.common.enums.SysResultCode;
import com.lizc.sports.sys.entity.Permission;
import com.lizc.sports.sys.sevice.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 权限控制
 * 
 * @author: lizc@sdhuijin.cn
 * @date: 2019-04-16 10:30
 **/
@RestController
@RequestMapping("home/permission")
public class PermissionController extends BaseController
{

    private final PermissionService permissionService;

    @Autowired
    public PermissionController(PermissionService permissionService)
    {
        this.permissionService = permissionService;
    }

    @RequestMapping("/save")
    public JsonResult savePermissionService(Permission permission)
    {
        JsonResult jsonResult = new JsonResult();
        try
        {
            permissionService.save(permission);
            jsonResult.setResultCode(SysResultCode.SUCCESS);
        }
        catch (Exception e)
        {
            jsonResult.setResultCode(SysResultCode.FAILURE);
            logger.debug(e.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping("/find-roots")
    public JsonResult<List<Permission>> findRoots()
    {
        JsonResult<List<Permission>> jsonResult = new JsonResult<>();
        try
        {
            List<Permission> roots = permissionService.findRoots();
            jsonResult.setResultCode(SysResultCode.SUCCESS);
            jsonResult.setResult(roots);
        }
        catch (Exception e)
        {
            jsonResult.setResultCode(SysResultCode.FAILURE);
        }
        return jsonResult;
    }
}
