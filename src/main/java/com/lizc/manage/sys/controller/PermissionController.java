package com.lizc.manage.sys.controller;


import com.lizc.manage.common.controller.TreeBaseController;
import com.lizc.manage.sys.entity.Permission;
import com.lizc.manage.sys.sevice.PermissionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 权限控制
 * 
 * @author: lizc
 * @date: 2019-04-16 10:30
 **/
@RestController
@RequestMapping("/home/permission")
public class PermissionController extends TreeBaseController<Permission, PermissionService>
{}
