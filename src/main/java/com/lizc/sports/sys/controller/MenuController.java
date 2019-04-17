package com.lizc.sports.sys.controller;


import com.lizc.sports.common.controller.BaseController;
import com.lizc.sports.common.dto.JsonResult;
import com.lizc.sports.common.enums.SysResultCode;
import com.lizc.sports.sys.entity.Menu;
import com.lizc.sports.sys.sevice.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 菜单
 * 
 * @author: lizc@sdhuijin.cn
 * @date: 2019-04-16 10:30
 **/
@RestController
@RequestMapping("home/menu")
public class MenuController extends BaseController
{

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService)
    {
        this.menuService = menuService;
    }

    @RequestMapping("/save")
    public JsonResult save(Menu menu)
    {
        JsonResult jsonResult = new JsonResult();
        try
        {
            menuService.save(menu);
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
    public JsonResult<List<Menu>> findRoots()
    {
        JsonResult<List<Menu>> jsonResult = new JsonResult<>();
        try
        {
            List<Menu> roots = menuService.findRoots();
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
