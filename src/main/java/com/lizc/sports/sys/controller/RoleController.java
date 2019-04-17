package com.lizc.sports.sys.controller;


import com.lizc.sports.common.controller.BaseController;
import com.lizc.sports.common.dto.JsonResult;
import com.lizc.sports.common.enums.SysResultCode;
import com.lizc.sports.sys.entity.Role;
import com.lizc.sports.sys.sevice.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author: lizc@sdhuijin.cn
 * @date: 2019-04-16 13:55
 **/
@RestController
@RequestMapping("home/role")
public class RoleController extends BaseController
{
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService)
    {
        this.roleService = roleService;
    }

    @RequestMapping("/save")
    public JsonResult save(Role role)
    {
        JsonResult jsonResult = new JsonResult();
        try
        {
            roleService.save(role);
            jsonResult.setResultCode(SysResultCode.SUCCESS);
        }
        catch (Exception e)
        {
            jsonResult.setResultCode(SysResultCode.FAILURE);
            logger.debug(e.getMessage());
        }
        return jsonResult;
    }
}
