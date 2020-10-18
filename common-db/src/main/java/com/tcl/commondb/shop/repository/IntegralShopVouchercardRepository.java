package com.tcl.commondb.shop.repository;

import com.tcl.commondb.shop.model.IntegralShopVouchercardModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface IntegralShopVouchercardRepository extends JpaRepository<IntegralShopVouchercardModel, Long>, JpaSpecificationExecutor<IntegralShopVouchercardModel>{

	@Query(value = "SELECT o FROM IntegralShopVouchercardModel o where o.goodsNo=:gno ORDER BY id DESC ")
	Page<IntegralShopVouchercardModel> getListAll(@Param("gno") String gno,Pageable pageable);

	IntegralShopVouchercardModel findByCardNoAndAndCardPwdAndGoodsNo(String cardno,String cardpwd,String gno);
	IntegralShopVouchercardModel findByCardPwdAndGoodsNo(String cardpwd,String gno);
	List<IntegralShopVouchercardModel> findAllByGoodsNo(String gno);
	IntegralShopVouchercardModel findByGoodsNoAndStatusOrderByIdAsc(String goodsno,Integer stt);

	@Modifying
	@Transactional
	@Query(value = "UPDATE integral_shop_vouchercard SET status =:stt  WHERE id = :id ", nativeQuery = true)
	int updateStatus(@Param("id") Long id, @Param("stt") Integer stt);

	@Transactional
	@Modifying
	@Query(nativeQuery = true,value = "DELETE FROM integral_shop_vouchercard WHERE card_no = '' and card_pwd='' and goods_no=:gno")
	int deleteRowBynull(@Param("gno") String gno);

}
