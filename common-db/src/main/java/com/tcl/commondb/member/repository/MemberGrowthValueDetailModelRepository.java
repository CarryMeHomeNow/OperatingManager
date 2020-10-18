package com.tcl.commondb.member.repository;

import com.tcl.commondb.member.model.MemberGrowthValueDetailModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberGrowthValueDetailModelRepository extends JpaRepository<MemberGrowthValueDetailModel, Long>, JpaSpecificationExecutor<MemberGrowthValueDetailModel> {

    MemberGrowthValueDetailModel findFirstBySsoid(String accountId);

    @Query(nativeQuery = true, value = "SELECT "
            + "d.source,"
            + "s.source_name,"
            + "sum( d.growth_value ) growth_value "
            + "FROM member_user_growth_value_detail d "
            + "LEFT JOIN member_growth_source s ON s.id = d.source "
            + "GROUP BY d.source ORDER BY d.source ASC")
    List<Object[]> queryGrowthDistribution();
}
