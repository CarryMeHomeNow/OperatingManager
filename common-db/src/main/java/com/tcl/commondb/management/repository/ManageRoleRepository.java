package com.tcl.commondb.management.repository;

import com.tcl.commondb.management.model.ManageRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManageRoleRepository extends JpaRepository<ManageRole, Long> {

    List<ManageRole> findAllByParentIdAndIsDelete(Long id,byte isDelete);
    ManageRole findFirstByName(String name);

    ManageRole findFirstByIdAndIsDelete(Long id,byte is_delete);

    List<ManageRole> findAllByIdInAndIsDelete(List<Long> ids,byte is_delete);

}
