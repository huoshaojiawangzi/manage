package com.lizc.sports.sys.bo;

import com.lizc.sports.sys.entity.Menu;
import com.lizc.sports.sys.entity.Permission;
import com.lizc.sports.sys.entity.Role;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/** Role缓存数据，由于Role中的@JSONField注解导致某些字段无法进行json转换，使用此类来保存缓存数据
 * @author: lizc@sdhuijin.cn
 * @date: 2019-05-05 11:04
 **/
@Data
public class RoleCache
{
    private String id;
    private String name;

    private List<Permission> permissions = new ArrayList<>();

    private List<Menu> menus = new ArrayList<>();

    public RoleCache generate(Role role) {
        this.id = role.getId();
        this.name = role.getName();
        this.permissions = role.getPermissions();
        this.menus = role.getMenus();
        return this;
    }

    public Role transform() {
        Role role = new Role();
        role.setId(this.id);
        role.setName(this.name);
        role.setPermissions(this.permissions);
        role.setMenus(this.menus);
        return role;
    }
}
