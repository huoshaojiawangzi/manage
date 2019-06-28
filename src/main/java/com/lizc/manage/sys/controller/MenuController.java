package com.lizc.manage.sys.controller;


import com.lizc.manage.common.controller.TreeBaseController;
import com.lizc.manage.sys.entity.Menu;
import com.lizc.manage.sys.sevice.MenuService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 菜单
 * 
 * @author: lizc@sdhuijin.cn
 * @date: 2019-04-16 10:30
 **/
@RestController
@RequestMapping("/home/menu")
public class MenuController extends TreeBaseController<Menu, MenuService>
{}
