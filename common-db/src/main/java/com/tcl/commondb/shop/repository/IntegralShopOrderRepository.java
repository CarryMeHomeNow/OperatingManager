package com.tcl.commondb.shop.repository;

import com.tcl.commondb.shop.model.IntegralShopOrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface IntegralShopOrderRepository extends JpaRepository<IntegralShopOrderModel, Long>, JpaSpecificationExecutor<IntegralShopOrderModel>{

	List<IntegralShopOrderModel> findAllByStatusAndReceovertimeLessThanEqual(Integer status,Long receovtime);
	@Modifying
	@Transactional
	@Query(value = "UPDATE integral_shop_order SET status =:stt,updatetime=:utime  WHERE id = :id ", nativeQuery = true)
	int updateStatus(@Param("id") Long id, @Param("stt") Integer stt, @Param("utime") String utime);

	@Modifying
	@Transactional
	@Query(value = "UPDATE integral_shop_order SET status=2, expresssn =:exno, paytime=:utime,receovertime=:receovertime WHERE id = :id ", nativeQuery = true)
	int updateexpress(@Param("id") Long id, @Param("exno") String exno, @Param("utime") String utime,  @Param("receovertime") Long receovertime);

}
