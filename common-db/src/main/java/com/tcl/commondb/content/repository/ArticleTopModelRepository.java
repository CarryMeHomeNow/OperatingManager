package com.tcl.commondb.content.repository;

import com.tcl.commondb.content.model.ArticleTopModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleTopModelRepository extends JpaRepository<ArticleTopModel, Long>, JpaSpecificationExecutor<ArticleTopModel> {

    List<ArticleTopModel> findByArticleIdEquals(Long articleId);

    @Modifying
    @Query("delete from ArticleTopModel t where t.articleId in :articleIds")
    void batchDelete(@Param("articleIds") List<Long> articleIds);

    void deleteByArticleIdEquals(Long articleId);
}
