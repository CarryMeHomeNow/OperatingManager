package com.tcl.commondb.advert.repository;

import com.tcl.commondb.advert.model.AdvertDepartmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AdvertDepartmentModelRepository extends JpaRepository<AdvertDepartmentModel, Long>, JpaSpecificationExecutor<AdvertDepartmentModel> {

    Optional<AdvertDepartmentModel> findById(Long id);

    AdvertDepartmentModel findFirstByIdEquals(Long id);

    List<AdvertDepartmentModel> findAllByIsDeleted(Integer isDeleted);

    List<AdvertDepartmentModel> findAllByIsDeletedAndType(Integer isDeleted, Integer type);

    List<AdvertDepartmentModel> findAllByIdIn(Set<Long> ids);

}
