package com.tcl.commondb.activity.repository;
import com.tcl.commondb.activity.model.MemMarketingLuckInvoiceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MemMarketingLuckInvoiceRepository extends JpaRepository<MemMarketingLuckInvoiceModel, Long>{

	@Query(value = "SELECT o FROM MemMarketingLuckInvoiceModel o ORDER BY id DESC ")
	Page<MemMarketingLuckInvoiceModel> getListAll(Pageable pageable);

	@Query(value = "SELECT o FROM MemMarketingLuckInvoiceModel o WHERE o.status=:stt AND o.sort=:sort ORDER BY id DESC ")
	Page<MemMarketingLuckInvoiceModel> getlistBysttsort(@Param("stt") Integer stt, @Param("sort") Integer sort, Pageable pageable);

	@Query(value = "SELECT o FROM MemMarketingLuckInvoiceModel o WHERE o.status=:stt ORDER BY id DESC ")
	Page<MemMarketingLuckInvoiceModel> getlistBystt(@Param("stt") Integer stt, Pageable pageable);
	@Query(value = "SELECT o FROM MemMarketingLuckInvoiceModel o WHERE o.sort=:sort ORDER BY id DESC ")
	Page<MemMarketingLuckInvoiceModel> getlistBysort(@Param("sort") Integer sort, Pageable pageable);

	@Query(value = "SELECT o FROM MemMarketingLuckInvoiceModel o WHERE o.mobile LIKE %:kw% ORDER BY id DESC ")
	Page<MemMarketingLuckInvoiceModel> getlistBykw(@Param("kw") String kw, Pageable pageable);

	MemMarketingLuckInvoiceModel findByMemUid(String memuid);
	MemMarketingLuckInvoiceModel findBySerialNum(String sn);
	
	MemMarketingLuckInvoiceModel findFirstByLuckEverdayIdAndMemUid(Long luckEverdayId, String memUid);

	@Modifying
	@Transactional
	@Query(value = "UPDATE mem_marketing_luck_invoice SET status =:stt  WHERE id = :id ", nativeQuery = true)
	int updateStatus(@Param("id") Long id, @Param("stt") Integer stt);
}
