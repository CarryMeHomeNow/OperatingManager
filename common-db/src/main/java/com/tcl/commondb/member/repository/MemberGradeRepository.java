package com.tcl.commondb.member.repository;

import com.tcl.commondb.member.model.MemberGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


/**
 * @author gaofan
 * 2020年8月10日15:21:51
 * 权益管理
 */
public interface MemberGradeRepository extends JpaRepository<MemberGrade,Integer> {

    List<MemberGrade> findByOrderByGradeAsc();
}
