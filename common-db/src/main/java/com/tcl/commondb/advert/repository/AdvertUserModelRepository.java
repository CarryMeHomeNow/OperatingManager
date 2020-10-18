package com.tcl.commondb.advert.repository;

import com.tcl.commondb.advert.model.AdvertUserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface AdvertUserModelRepository extends JpaRepository<AdvertUserModel, Long>, JpaSpecificationExecutor<AdvertUserModel> {

    Optional<AdvertUserModel> findById(Long id);

    AdvertUserModel findByIdEquals(Long id);

    AdvertUserModel findFirstByUsername(String username);

    AdvertUserModel findFirstByMid(Long mid);

    AdvertUserModel findFirstByIsDeletedAndAuditStatusAndStatusAndUsername(int isDeleted, int auditStatus, int status, String username);

    AdvertUserModel findFirstByIsDeletedAndUsername(int isDeleted, String username);

    Page<AdvertUserModel> findAllByIsDeleted(int isDelete, Pageable pageable);

    Page<AdvertUserModel> findAllByIsDeletedAndAuditStatus(int isDelete, int auditStatus, Pageable pageable);

    Page<AdvertUserModel> findAllByIsDeletedAndAuditStatusAndDepartmentId(int isDelete, int auditStatus, long departmentId, Pageable pageable);

    Page<AdvertUserModel> findAllByIsDeletedAndDepartmentId(int isDelete, long departmentId, Pageable pageable);



}
