package com.lizc.manage.pc.user.controller;


import com.lizc.manage.common.controller.PageableBaseController;
import com.lizc.manage.pc.user.entity.User;
import com.lizc.manage.pc.user.service.UserService;
import com.lizc.manage.pc.user.vo.UserSearchModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/home/user")
public class UserController extends PageableBaseController<User, UserSearchModel, UserService>
{}
