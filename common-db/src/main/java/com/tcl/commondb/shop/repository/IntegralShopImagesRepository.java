package com.tcl.commondb.shop.repository;

import com.tcl.commondb.shop.model.IntegralShopImagesModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface IntegralShopImagesRepository extends JpaRepository<IntegralShopImagesModel, Long>, JpaSpecificationExecutor<IntegralShopImagesModel>{

	@Query(value = "SELECT o FROM IntegralShopImagesModel o ORDER BY id DESC ")
	Page<IntegralShopImagesModel> getListAll(Pageable pageable);

	/*
        @Query(value = "SELECT o FROM IntegralShopImagesModel o WHERE o.status >-1 AND (o.name LIKE %:kw% OR o.mobile LIKE %:kw%) ORDER BY id DESC ")
        Page<IntegralShopImagesModel> getByKeywords(@Param("kw") String kw, Pageable pageable);

        @Query(value = "SELECT o FROM IntegralShopImagesModel o WHERE o.status=:stt AND (o.name LIKE %:kw% OR o.mobile LIKE %:kw%) ORDER BY id DESC ")
        Page<IntegralShopImagesModel> getBysttLikekw(@Param("kw") String kw, @Param("stt") Integer stt, Pageable pageable);
    */
	List<IntegralShopImagesModel> findByComefromAndLinkflag(String cf,String linkflag);

	@Modifying
	@Transactional
	@Query(value = "UPDATE integral_shop_images SET status =:stt  WHERE id = :id ", nativeQuery = true)
	int updateStatus(@Param("id") Long id, @Param("stt") Integer stt);

}
