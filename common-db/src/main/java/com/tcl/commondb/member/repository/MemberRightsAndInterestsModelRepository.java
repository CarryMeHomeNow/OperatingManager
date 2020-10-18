package com.tcl.commondb.member.repository;


import com.tcl.commondb.member.model.MemberRightsAndInterestsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MemberRightsAndInterestsModelRepository extends JpaRepository<MemberRightsAndInterestsModel, Long>, JpaSpecificationExecutor<MemberRightsAndInterestsModel> {

	MemberRightsAndInterestsModel findGrowthValueBySsoid(Long ssoid);

	MemberRightsAndInterestsModel findBySsoid(Long ssoid);

	int countBySsoid(Long ssoid);
}
