package com.tcl.commondb.content.repository;

import com.tcl.commondb.content.model.ArticleSectionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleSectionModelRepository extends JpaRepository<ArticleSectionModel, Long>, JpaSpecificationExecutor<ArticleSectionModel> {

    List<ArticleSectionModel> findByUseStatusOrderByPositionAscCreateTimeDesc(@Param("useStatus") Integer useStatus);

    ArticleSectionModel findByIdEquals(Long id);

    List<ArticleSectionModel> findByUseStatusOrderByPosition(@Param("useStatus") Integer useStatus);
}
