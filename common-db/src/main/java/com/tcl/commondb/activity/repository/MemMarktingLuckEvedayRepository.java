package com.tcl.commondb.activity.repository;

import com.tcl.commondb.activity.model.MemMarktingLuckEvedayModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface MemMarktingLuckEvedayRepository extends JpaRepository<MemMarktingLuckEvedayModel, Long>, JpaSpecificationExecutor<MemMarktingLuckEvedayModel>{

/*	@Query(value = "SELECT o FROM mem_markting_luck_eveday o WHERE o.status =1 ORDER BY id DESC ")
	Page<MemMarktingLuckEvedayModel> findByStatusAll(Pageable pageable);

	@Query(value = "SELECT o FROM mem_markting_luck_eveday o WHERE o.status >-1 AND ( o.index_title LIKE %:kw% OR o.luck_title LIKE %:kw%) ORDER BY id DESC ")
	Page<MemMarktingLuckEvedayModel> findByKeywords(@Param("kw") String kw, Pageable pageable);

	@Query(value = "SELECT o FROM mem_markting_luck_eveday o WHERE o.status=:stt AND o.status>-1 AND (o.index_title LIKE %:kw% OR o.luck_title LIKE %:kw%) ORDER BY id DESC ")
	Page<MemMarktingLuckEvedayModel> findBysttLikekw(@Param("kw") String kw, @Param("stt") Integer stt, Pageable pageable);*/

	
	MemMarktingLuckEvedayModel findFirstByLuckAid(Long luckAid);
}
