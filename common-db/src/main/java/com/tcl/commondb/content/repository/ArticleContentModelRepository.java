package com.tcl.commondb.content.repository;

import com.tcl.commondb.content.model.ArticleContentModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ArticleContentModelRepository extends JpaRepository<ArticleContentModel, Long>, JpaSpecificationExecutor<ArticleContentModel> {

    ArticleContentModel findByIdEquals(Long id);

    List<ArticleContentModel> queryByIdIn(@Param("articleIds") List<Long> articleIds);

    @Modifying
    @Query(value = "update ArticleContentModel p set p.useStatus=0 where p.id in (:articleIds)")
    int deleteArticle(@Param("articleIds") List<Long> articleIds);

    @Modifying
    @Query(value = "update ArticleContentModel p set p.auditStatus=:auditStatus,p.status=:status where p.id in (:articleIds)")
    int auditContent(@Param("auditStatus") String auditStatus,@Param("status") String status,@Param("articleIds") List<Long> articleIds);

    @Modifying
    @Query(value = "update ArticleContentModel p set p.auditStatus=:auditStatus,p.status=:status,p.remarks=:remarks where p.id in (:articleIds)")
    int auditContentReject(@Param("auditStatus") String auditStatus,@Param("status") String status,@Param("articleIds") List<Long> articleIds,@Param("remarks") String remarks);

    @Modifying
    @Query(value = "update ArticleContentModel p set p.auditStatus=:auditStatus,p.status=:status, "
            + "p.publishTime =:publishTime where p.id in (:articleIds)")
    int publist(@Param("auditStatus") String auditStatus, @Param("status") String status, @Param("articleIds") List<Long> articleIds, @Param("publishTime") Date publishTime);


    /**
     * 根据审核状态，文章删除状态，查询文章列表，置顶的文章在前面，置顶已过期的文章将置顶为赋值为int最大值加100再排序
     * @param auditStatus
     * @param useStatus
     * @param articleIds
     * @param pageable
     * @return
     */
    @Query(nativeQuery = true,
            value = "select a.id,"
                    + "a.advertisement,"
                    + "a.article_cover,"
                    + "a.title,"
                    + "a.browse_num,"
                    + "a.browse_base,"
                    + "a.create_time,"
                    + "a.content_html,"
                    + "if(a.top_expired_time>NOW(),a.top_position, 2147483747) isTop "
                    + "from article_content_configure a where a.status=:status "
                    + "and a.audit_status=:auditStatus "
                    + "and a.use_status=:useStatus "
                    + "and (coalesce(:articleIds,null) is null or a.id in (:articleIds)) "
                    + "order by isTop asc, create_time desc /* #pageable# */ ",
            countQuery = "select count(*) from article_content_configure a "
                        + "where a.status=:status "
                        + "and a.audit_status=:auditStatus "
                        + "and a.use_status=:useStatus "
                        + "and (coalesce(:articleIds,null) is null or a.id in (:articleIds)) ")
    Page<Object[]> appArticleList(@Param("auditStatus") String auditStatus, @Param("useStatus") Integer useStatus, @Param("status") String status,
                                  @Param("articleIds") List<Long> articleIds, Pageable pageable);

    @Query(nativeQuery = true,value = "select a.id from article_content_configure a "
            + "where a.status=:status and a.audit_status=:auditStatus and a.use_status=:useStatus "
            + "and (coalesce(:articleIds,null) is null or a.id in (:articleIds)) ")
    List<Object> queryApprovedArticle(@Param("auditStatus") String auditStatus, @Param("useStatus") Integer useStatus,
                                      @Param("status") String status, @Param("articleIds") List<Long> articleIds);

    @Modifying
    @Query(value = "update ArticleContentModel a set a.browseNum = a.browseNum + :num where a.id = :articleId")
    int addBrowseNum(@Param("articleId") Long articleId, @Param("num") Integer num);


    @Query(value = "select a.id,a.advertisement,a.articleCover,a.title,a.browseNum,a.browseBase,a.createTime,a.contentHtml "
            + "from ArticleContentModel a where a.id in (:articleIds) ")
    List<Object[]> queryAppArticleRand(@Param("articleIds") List<Long> articleIds);

    @Modifying
    @Query(value = "update ArticleContentModel p set p.browseBase=:browseBase where p.id =:articleId")
    int updateBrowse(@Param("browseBase") Integer browseBase,@Param("articleId") Long articleId);

    @Query(value = "select a.id,a.advertisement,a.articleCover,a.title,a.browseNum,a.browseBase,a.contentTangram, "
            + "a.contentHtml,a.publishTime,a.createTime "
            + "from ArticleContentModel a where a.id =:articleId ")
    List<Object[]> queryArticleById(@Param("articleId") Long articleId);
}
