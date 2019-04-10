package com.lizc.sports.sys.sevice;

import com.lizc.sports.common.service.BaseService;
import com.lizc.sports.sys.entity.Menu;
import com.lizc.sports.sys.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**菜单业务逻辑
 * @author: lizc@sdhuijin.cn
 * @date: 2019-03-06 11:25
 **/
@Service
public class MenuService extends BaseService<Menu,String,MenuRepository> {

    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> findRoots()
    {
        return menuRepository.findRoots();
    }
}
