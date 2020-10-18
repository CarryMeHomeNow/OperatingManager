package com.tcl.commondb.management.repository;

import com.tcl.commondb.management.model.ManageMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 */
public interface ManageMenuRepository extends JpaRepository<ManageMenu, Long> {

    List<ManageMenu> findAllByParentId(Integer parentId);
    List<ManageMenu> findAllByMenuId(Integer menuId);
}
