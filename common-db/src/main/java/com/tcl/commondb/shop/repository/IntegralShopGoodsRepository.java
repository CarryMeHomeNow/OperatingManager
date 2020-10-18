package com.tcl.commondb.shop.repository;

import com.tcl.commondb.shop.model.IntegralShopGoodsModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface IntegralShopGoodsRepository extends JpaRepository<IntegralShopGoodsModel, Long>, JpaSpecificationExecutor<IntegralShopGoodsModel>{

	@Query(value = "SELECT o FROM IntegralShopGoodsModel o where o.status>-1 ORDER BY id DESC ")
	Page<IntegralShopGoodsModel> getListAll(Pageable pageable);

	@Query(value = "SELECT o FROM IntegralShopGoodsModel o WHERE o.brandId=:bid AND o.status>-1 ORDER BY id DESC ")
	Page<IntegralShopGoodsModel> getBybidAll(@Param("bid") Long bid, Pageable pageable);


	@Query(value = "SELECT o FROM IntegralShopGoodsModel o WHERE o.status=:stt " +
			"AND (o.sku LIKE %:kw% OR o.goodsName LIKE %:kw%) ORDER BY id DESC ")
	Page<IntegralShopGoodsModel> getBysttLikekw( @Param("stt") Integer stt,@Param("kw") String kw, Pageable pageable);

	@Query(value = "SELECT o FROM IntegralShopGoodsModel o WHERE o.status>-1 AND" +
			"(o.sku LIKE %:kw% OR o.goodsName LIKE %:kw%) ORDER BY id DESC ")
	Page<IntegralShopGoodsModel> getByLikekw(@Param("kw") String kw, Pageable pageable);

	@Query(value = "SELECT o FROM IntegralShopGoodsModel o WHERE o.status=:stt ORDER BY id DESC ")
	Page<IntegralShopGoodsModel> getBystt(@Param("stt") Integer stt, Pageable pageable);

    IntegralShopGoodsModel findBygoodssn(String gno);

	@Query(value = "SELECT goods.* FROM integral_shop_goods goods order by id desc limit 1 ",nativeQuery=true)
	IntegralShopGoodsModel getLastrow();

	@Modifying
	@Transactional
	@Query(value = "UPDATE integral_shop_goods SET status =:stt,modified_time=:utime  WHERE id = :id ", nativeQuery = true)
	int updateStatus(@Param("id") Long id, @Param("stt") Integer stt, @Param("utime") String utime);

	@Modifying
	@Transactional
	@Query(value = "UPDATE integral_shop_goods SET list_order =:sort  WHERE id = :id ", nativeQuery = true)
	int updateOrder(@Param("id") Long id, @Param("sort") Integer sort);


	/**
	 * 查询商品库存
	 * @param id 商品id
	 * @return
	 */
	@Query(value = "SELECT o FROM IntegralShopGoodsModel o where id=:id")
	IntegralShopGoodsModel getStock(@Param("id") Long id);

	/**
	 * 乐观锁方案扣减库存
	 * @param id 商品id
	 * @param withholdnum 版本号
	 * @return
	 */
	@Modifying
	@Transactional
	@Query(value = "UPDATE integral_shop_goods SET storge = storge - 1, withholdnum = withholdnum + 1 WHERE id = :id AND storge > 0 AND withholdnum = :withholdnum", nativeQuery = true)
	int desStockForWithholdnum(@Param("id") Long id, @Param("withholdnum") Long withholdnum);

}
