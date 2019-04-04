package com.lizc.sports.sys.sevice;

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
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public Menu get(String id)
    {
        return menuRepository.findById(id).get();
    }

    public void save(Menu menu)
    {
        menuRepository.save(menu);
    }

    public void saveAndFlush(Menu menu)
    {
        menuRepository.saveAndFlush(menu);
    }

    public void delete(Menu menu)
    {
        menuRepository.delete(menu);
    }

    public List<Menu> findRoots()
    {
        return menuRepository.findRoots();
    }

    public List<Menu> findAll(){return menuRepository.findAll();}
}
