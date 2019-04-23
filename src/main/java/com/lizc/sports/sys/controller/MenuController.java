package com.lizc.sports.sys.controller;


import com.lizc.sports.common.controller.TreeBaseController;
import com.lizc.sports.sys.entity.Menu;
import com.lizc.sports.sys.sevice.MenuService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 菜单
 * 
 * @author: lizc@sdhuijin.cn
 * @date: 2019-04-16 10:30
 **/
@RestController
@RequestMapping("home/menu")
public class MenuController extends TreeBaseController<Menu, MenuService>
{}
