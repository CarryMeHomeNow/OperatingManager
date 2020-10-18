package com.tcl.commondb.management.repository;

import com.tcl.commondb.management.model.ManageMenuRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 */
public interface ManageMenuRoleRepository extends JpaRepository<ManageMenuRole, Long> {

    public List<ManageMenuRole> findAllBycId(Long cid);
    public void deleteAllBycId(Long cid);
}
