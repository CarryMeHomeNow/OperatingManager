package com.tcl.commondb.management.repository;

import com.tcl.commondb.management.model.ManageUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ManageUserRepository extends JpaRepository<ManageUser, Long> {

    @Query(value = "SELECT user FROM ManageUser user " +
            "WHERE (user.username LIKE %:search% OR user.email LIKE %:search% OR user.phone LIKE %:search%)", nativeQuery = false)
    Page<ManageUser> findUserByLikeSearch(@Param("search") String search, Pageable pageable);

    Page<ManageUser> findAllByUsernameLikeOrEmailLikeOrPhoneLike(String search1,String search2,String search3,Pageable pageable);

    Page<ManageUser> findAllByDepartmentIdIn(List<String> deparsIds, Pageable pageable);
    ManageUser findFirstByUsername(String username);
}
