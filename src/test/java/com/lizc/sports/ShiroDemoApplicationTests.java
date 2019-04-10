package com.lizc.sports;

import com.lizc.sports.sys.entity.Menu;
import com.lizc.sports.sys.entity.Permission;
import com.lizc.sports.sys.sevice.MenuService;
import com.lizc.sports.sys.sevice.PermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroDemoApplicationTests {

	@Autowired
	private MenuService menuService;

	@Autowired
	private PermissionService permissionService;

	/**
	 * 测试没有任何注释的menu以及permission保存
	 */
	@Test
	public void testExposeSave() {
		Permission permission = permissionService.findAll().get(0);
		Menu menu = new Menu();
		menu.setName("档案管理查询");
		menu.setPath("archiveList");
		permissionService.save(permission);
		menuService.save(menu);
	}

	/**
	 * 测试没有任何注释的menu以及permission获取
	 */
	@Test
	public void testExposeGet()
	{
		List<Menu> menus = menuService.findAll();
		List<Permission> permissions = permissionService.findAll();
	}

	/**
	 * 测试给permission中的list增加一个menu，保存，查看是否menu也进行了保存
	 */
	@Test
	@Transactional
	public void testSave()
	{
		Permission permission = permissionService.get("402881ef69e6e0340169e6e053f30000");
		Menu menu = new Menu();
		menu.setPath("sunList");
		menu.setName("开心查询");
		permissionService.saveAndFlush(permission);
	}

}

