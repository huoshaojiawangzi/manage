package com.lizc.sports.pc.demo.controller;


import com.lizc.sports.common.controller.PageableBaseController;
import com.lizc.sports.pc.demo.entity.User;
import com.lizc.sports.pc.demo.service.UserService;
import com.lizc.sports.pc.demo.vo.UserSearchModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/home/user")
public class UserController extends PageableBaseController<User, UserSearchModel, UserService>
{}
