package com.tcl.commondb.activity.repository;

import com.tcl.commondb.activity.model.MemMarktingLuckCardlistModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface MemMarktingLuckCardlistRepository extends JpaRepository<MemMarktingLuckCardlistModel, Long>, JpaSpecificationExecutor<MemMarktingLuckCardlistModel>{

	@Query(value = "SELECT o FROM MemMarktingLuckCardlistModel o where o.prizeNumber=:pno ORDER BY id DESC ")
	Page<MemMarktingLuckCardlistModel> getListAll(@Param("pno") String pno, Pageable pageable);

	@Query(value = "SELECT o FROM MemMarktingLuckCardlistModel o WHERE o.status >-1 AND (o.cardNo LIKE %:kw% OR o.cardPwd LIKE %:kw%) ORDER BY id DESC ")
	Page<MemMarktingLuckCardlistModel> getByKeywords(@Param("kw") String kw, Pageable pageable);

	@Query(value = "SELECT o FROM MemMarktingLuckCardlistModel o WHERE o.status=:stt AND (o.cardNo LIKE %:kw% OR o.cardPwd LIKE %:kw%) ORDER BY id DESC ")
	Page<MemMarktingLuckCardlistModel> getBysttLikekw(@Param("kw") String kw, @Param("stt") Integer stt, Pageable pageable);

	MemMarktingLuckCardlistModel findByCardNoAndAndCardPwdAndPrizeNumber(String cardno, String cardpwd, String pno);

	MemMarktingLuckCardlistModel findByCardPwdAndPrizeNumber(String cardpwd, String pno);
	
	MemMarktingLuckCardlistModel findFirstByPrizeNumberAndStatusAndStartTimeLessThanAndEndTimeGreaterThanOrderByCteateTimeAsc(String prizeNumber, Integer status, String startTime, String endTime);
	
	int countByPrizeNumber(String prizeNumber);
	
	int countByPrizeNumberAndStatus(String prizeNumber, Integer status);
	
	int countByPrizeNumberAndEndTimeLessThan(String prizeNumber, String endTime);

	List<MemMarktingLuckCardlistModel> findAllByPrizeNumber(String pno);

	@Modifying
	@Transactional
	@Query(value = "UPDATE mem_markting_luck_cardlist SET status =:stt,win_id=:winid  WHERE id = :id ", nativeQuery = true)
	int updaterow(@Param("id") Long id, @Param("stt") Integer stt, @Param("winid") Integer winid);

	@Transactional
	@Modifying
	@Query(nativeQuery = true,value = "DELETE FROM mem_markting_luck_cardlist WHERE card_no = '' and card_pwd='' and prize_number=:prizenum")
	int deleteRowBynull(@Param("prizenum") String prizenum);

}
