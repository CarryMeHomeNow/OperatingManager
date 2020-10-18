package com.tcl.commondb.advert.repository;

import com.tcl.commondb.advert.model.AdvertUserRoleModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdvertUserRoleModelRepository extends JpaRepository<AdvertUserRoleModel, Long>, JpaSpecificationExecutor<AdvertUserRoleModel> {

    AdvertUserRoleModel findFirstByUserId(Long userId);
    List<AdvertUserRoleModel> findAllByUserId(Long userId);
    Page<AdvertUserRoleModel> findAllByRoleId(Long roleId, Pageable pageable);

    @Query(value = "SELECT r.name FROM advert_user_role u, advert_role r WHERE r.id = u.role_id AND u.user_id = :userId ", nativeQuery = true)
    String findRoleNameByUserId(@Param("userId") Long userId);
}
