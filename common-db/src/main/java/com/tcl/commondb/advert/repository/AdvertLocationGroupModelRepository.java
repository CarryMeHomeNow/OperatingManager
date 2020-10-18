package com.tcl.commondb.advert.repository;

import com.tcl.commondb.advert.model.AdvertLocationGroupModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Set;

public interface AdvertLocationGroupModelRepository extends JpaRepository<AdvertLocationGroupModel, Long>, JpaSpecificationExecutor<AdvertLocationGroupModel> {

    AdvertLocationGroupModel findByIdEquals(Long id);

    AdvertLocationGroupModel findFirstByCodeEquals(String code);

    List<AdvertLocationGroupModel> findAllByIdIn(Set<Long> ids);

    List<AdvertLocationGroupModel> findAllByIsDeletedAndStatusOrderById(Integer isDelete, Integer status);
}
