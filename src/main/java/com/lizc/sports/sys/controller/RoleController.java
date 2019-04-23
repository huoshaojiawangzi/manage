package com.lizc.sports.sys.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lizc.sports.common.controller.PageableBaseController;
import com.lizc.sports.sys.entity.Role;
import com.lizc.sports.sys.sevice.RoleService;
import com.lizc.sports.sys.vo.RoleSearchModel;


/**
 * @author: lizc@sdhuijin.cn
 * @date: 2019-04-16 13:55
 **/
@RestController
@RequestMapping("home/role")
public class RoleController extends PageableBaseController<Role, RoleSearchModel, RoleService>
{}
