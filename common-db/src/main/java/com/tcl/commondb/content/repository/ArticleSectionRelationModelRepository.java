package com.tcl.commondb.content.repository;

import com.tcl.commondb.content.model.ArticleSectionRelationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleSectionRelationModelRepository extends JpaRepository<ArticleSectionRelationModel, Long>, JpaSpecificationExecutor<ArticleSectionRelationModel> {

    ArticleSectionRelationModel findByIdEquals(Long id);

    List<ArticleSectionRelationModel> findBySectionId(Long sectionId);

    List<ArticleSectionRelationModel> findByArticleIdIn(List<Long> articleId);

    List<ArticleSectionRelationModel> findByArticleId(Long articleId);

    @Modifying
    @Query(value = "delete from ArticleSectionRelationModel p where p.articleId=:articleId")
    int deleteRelationByArticleId(@Param("articleId") Long articleId);

    @Modifying
    @Query(value = "delete from ArticleSectionRelationModel p where p.sectionId=:sectionId")
    int deleteRelationBySectionId(@Param("sectionId") Long sectionId);

    @Query(value = "select a.id "
            + "from article_content_configure a,article_section_relation r "
            + "where  r.section_id=:sectionId "
            + "and a.id = r.article_id "
            + "and a.status='2' "
            + "and a.audit_status='2' "
            + "and a.use_status=1", nativeQuery = true)
    List<Long> queryEffectiveArticleId(@Param("sectionId") Long sectionId);
}
