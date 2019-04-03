package com.lizc.sports.sys.repository;

import com.lizc.sports.sys.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author: lizc@sdhuijin.cn
 * @date: 2019-03-06 11:15
 **/
public interface MenuRepository extends JpaRepository<Menu, String>, JpaSpecificationExecutor<Menu> {
    @Query("select o from Menu o where parent.id is null")
    List<Menu> findRoots();
}
