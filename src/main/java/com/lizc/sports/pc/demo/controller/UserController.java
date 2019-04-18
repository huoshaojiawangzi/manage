package com.lizc.sports.pc.demo.controller;


import com.lizc.sports.common.controller.BaseController;
import com.lizc.sports.common.dto.JsonResult;
import com.lizc.sports.common.enums.SysResultCode;
import com.lizc.sports.pc.demo.entity.User;
import com.lizc.sports.pc.demo.service.UserService;
import com.lizc.sports.pc.demo.vo.UserSearchModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("home/user")
public class UserController extends BaseController<User,UserService>
{
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/find-page")
    public JsonResult<Page<User>> findPgae(UserSearchModel searchModel)
    {
        JsonResult<Page<User>> jsonResult = new JsonResult<>();
        Page<User> page = userService.findPage(searchModel);
        jsonResult.setResultCode(SysResultCode.SUCCESS);
        jsonResult.setResult(page);
        return jsonResult;
    }

}
