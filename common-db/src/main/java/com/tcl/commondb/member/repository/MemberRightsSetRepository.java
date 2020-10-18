package com.tcl.commondb.member.repository;

import com.tcl.commondb.member.model.MemberRightsSet;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author gaofan
 * 2020年8月10日18:12:06
 * 权益设置
 */
public interface MemberRightsSetRepository extends JpaRepository<MemberRightsSet, Integer> {

}
