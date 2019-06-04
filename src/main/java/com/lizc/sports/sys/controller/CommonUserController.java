package com.lizc.sports.sys.controller;

import com.lizc.sports.common.controller.BaseController;
import com.lizc.sports.common.dto.JsonResult;
import com.lizc.sports.common.enums.SysResultCode;
import com.lizc.sports.sys.entity.CommonUser;
import com.lizc.sports.sys.sevice.CommonUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("home/commonUser")
public class CommonUserController extends BaseController<CommonUser, CommonUserService>
{
    /**
     * 根据登录名，获得用户信息
     * @param userName 登录名
     * @return 包含用户信息的json实体
     */
    @GetMapping("/find-by-userName")
    public JsonResult<CommonUser> findByUserName(String userName)
    {
        JsonResult<CommonUser> jsonResult = new JsonResult<>();
        CommonUser commonUser = service.findByUserName(userName);
        jsonResult.setResult(commonUser);
        jsonResult.setResultCode(SysResultCode.SUCCESS);
        return jsonResult;
    }

}