package com.lizc.sports.sys.controller;


import com.lizc.sports.common.controller.BaseController;
import com.lizc.sports.sys.entity.CommonUser;
import com.lizc.sports.sys.sevice.CommonUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/home/commonUser")
public class CommonUserController extends BaseController<CommonUser, CommonUserService>
{}