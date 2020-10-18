package com.tcl.commondb.advert.repository;

import com.tcl.commondb.advert.model.AdvertRoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvertRoleModelRepository extends JpaRepository<AdvertRoleModel, Long> {
    List<AdvertRoleModel> findAllByIsDeleted(int isDelete);

    AdvertRoleModel findFirstByIsDeletedAndName(int isDelete, String name);

    AdvertRoleModel findFirstByIsDeletedAndId(int isDeleted, Long id);

    List<AdvertRoleModel> findAllByIsDeletedAndIdIn(int isDeleted, List<Long> ids);
}
