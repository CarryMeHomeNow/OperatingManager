package com.tcl.commondb.management.repository;

import com.tcl.commondb.management.model.ManageUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManageUserRoleRepository extends JpaRepository<ManageUserRole, Long> {



    ManageUserRole findFirstBymId(Long mId);
    List<ManageUserRole> findAllBymIdIn(List<Long> mids);
}
