package com.lizc.sports.sys.controller;


import com.lizc.sports.common.controller.BaseController;
import com.lizc.sports.common.dto.JsonResult;
import com.lizc.sports.common.enums.SysResultCode;
import com.lizc.sports.sys.entity.Role;
import com.lizc.sports.sys.sevice.RoleService;
import com.lizc.sports.sys.vo.RoleSearchModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author: lizc@sdhuijin.cn
 * @date: 2019-04-16 13:55
 **/
@RestController
@RequestMapping("home/role")
public class RoleController extends BaseController<Role,RoleService>
{
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService)
    {
        this.roleService = roleService;
    }

    @RequestMapping("/find-page")
    public JsonResult<Page<Role>> findPage(RoleSearchModel searchModel)
    {
        JsonResult<Page<Role>> jsonResult = new JsonResult<>();
        Page<Role> page = roleService.findPage(searchModel);
        jsonResult.setResultCode(SysResultCode.SUCCESS);
        jsonResult.setResult(page);
        return jsonResult;
    }
}
