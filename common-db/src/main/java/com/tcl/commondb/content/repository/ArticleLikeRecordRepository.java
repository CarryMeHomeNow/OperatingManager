package com.tcl.commondb.content.repository;


import com.tcl.commondb.content.model.ArticleLikeRecordModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleLikeRecordRepository extends JpaRepository<ArticleLikeRecordModel, Long>, JpaSpecificationExecutor<ArticleLikeRecordModel> {

    List<ArticleLikeRecordModel> findByArticleIdAndSsoid(@Param("articleId") Long articleId, @Param("ssoid") Long ssoid);

    @Modifying
    @Query(value = "update ArticleLikeRecordModel a set a.likeStatus =:likeStatus where a.articleId =:articleId and a.ssoid =:ssoid")
    int updateLikeStatus(@Param("articleId") Long articleId, @Param("ssoid") Long ssoid, @Param("likeStatus") Integer likeStatus);

    long countByArticleIdAndSsoidAndLikeStatus(@Param("articleId") Long articleId, @Param("ssoid") Long ssoid,
                                               @Param("likeStatus") Integer likeStatus);

    int countByArticleIdAndLikeStatus(Long articleId, Integer likeStatus);
}
