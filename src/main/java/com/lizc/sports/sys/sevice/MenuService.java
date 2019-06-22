package com.lizc.sports.sys.sevice;


import com.lizc.sports.common.service.TreeBaseService;
import com.lizc.sports.sys.entity.Menu;
import com.lizc.sports.sys.repository.MenuRepository;
import org.springframework.stereotype.Service;


/**
 * 菜单业务逻辑
 * 
 * @author: lizc@sdhuijin.cn
 * @date: 2019-03-06 11:25
 **/
@Service
public class MenuService extends TreeBaseService<Menu, String, MenuRepository>
{}
