package com.lizc.manage.sys.controller;


import com.lizc.manage.common.controller.BaseController;
import com.lizc.manage.sys.entity.CommonUser;
import com.lizc.manage.sys.sevice.CommonUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/home/commonUser")
public class CommonUserController extends BaseController<CommonUser, CommonUserService>
{}