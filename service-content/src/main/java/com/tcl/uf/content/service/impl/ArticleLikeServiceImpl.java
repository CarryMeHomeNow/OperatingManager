package com.tcl.uf.content.service.impl;

import com.tcl.commondb.content.model.ArticleLikeRecordModel;
import com.tcl.commondb.content.repository.ArticleLikeRecordRepository;
import com.tcl.uf.content.consts.ArticleConstants;
import com.tcl.uf.content.dto.AppArticleLikeParams;
import com.tcl.uf.content.service.ArticleLikeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("articleLikeService")
public class ArticleLikeServiceImpl implements ArticleLikeService {

    private static final Logger log = LoggerFactory.getLogger(ArticleLikeServiceImpl.class);

    @Autowired
    private ArticleLikeRecordRepository articleLikeRecordRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void like(AppArticleLikeParams params) {
        List<ArticleLikeRecordModel> list = articleLikeRecordRepository.findByArticleIdAndSsoid(params.getArticleId(), params.getSsoid());
        // 不存在点赞记录
        if (list.isEmpty()) {
            ArticleLikeRecordModel model = new ArticleLikeRecordModel();
            model.setArticleId(params.getArticleId());
            model.setCreateTime(new Date());
            model.setLikeStatus(ArticleConstants.STATUS_LIKE);
            model.setSsoid(params.getSsoid());
            articleLikeRecordRepository.saveAndFlush(model);
            log.info("点赞成功：{},{}", params.getArticleId(), params.getSsoid());
        } else if (ArticleConstants.STATUS_NOT_LIKE == list.get(0).getLikeStatus()) {
            articleLikeRecordRepository.updateLikeStatus(params.getArticleId(), params.getSsoid(), ArticleConstants.STATUS_LIKE);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelLike(AppArticleLikeParams params) {
        List<ArticleLikeRecordModel> list = articleLikeRecordRepository.findByArticleIdAndSsoid(params.getArticleId(), params.getSsoid());
        if (!list.isEmpty() && ArticleConstants.STATUS_LIKE == list.get(0).getLikeStatus()) {
            articleLikeRecordRepository.updateLikeStatus(params.getArticleId(), params.getSsoid(), ArticleConstants.STATUS_NOT_LIKE);
            log.info("取消点赞：{},{}", params.getArticleId(), params.getSsoid());
        }
    }
}
