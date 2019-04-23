package com.lizc.sports.sys.controller;


import com.lizc.sports.common.controller.TreeBaseController;
import com.lizc.sports.sys.entity.Permission;
import com.lizc.sports.sys.sevice.PermissionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 权限控制
 * 
 * @author: lizc@sdhuijin.cn
 * @date: 2019-04-16 10:30
 **/
@RestController
@RequestMapping("home/permission")
public class PermissionController extends TreeBaseController<Permission, PermissionService>
{}
