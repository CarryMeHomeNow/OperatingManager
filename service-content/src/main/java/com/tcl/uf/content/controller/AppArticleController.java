package com.tcl.uf.content.controller;


import com.tcl.uf.common.base.dto.AbstractBaseController;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.dto.token.TokenAppUserInfo;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.content.consts.ArticleConstants;
import com.tcl.uf.content.dto.AppArticleLikeParams;
import com.tcl.uf.content.dto.AppArticleListParams;
import com.tcl.uf.content.dto.AppArticleRandParams;
import com.tcl.uf.content.service.ArticleContentService;
import com.tcl.uf.content.service.ArticleLikeService;
import com.tcl.uf.content.service.ArticleSectionService;
import com.tcl.uf.content.vo.AppArticleDetailsVo;
import com.tcl.uf.content.vo.AppArticleListVo;
import com.tcl.uf.content.vo.ArticleSectionVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(description = "APP端文章内容")
@RestController
@RequestMapping("/app/article")
public class AppArticleController extends AbstractBaseController {

    private static final Logger _log = LoggerFactory.getLogger(AppArticleController.class);

    @Autowired
    private ArticleContentService articleContentService;

    @Autowired
    private ArticleLikeService articleLikeService;

    @Autowired
    private ArticleSectionService articleSectionService;

    @PostMapping(value = "/list")
    @ApiOperation(value = "APP端查看文章列表", notes = "APP端查看文章列表", httpMethod = "POST")
    public ResponseData<ListWithPage<AppArticleListVo>> appArticleList(@RequestBody AppArticleListParams params) {
        ListWithPage<AppArticleListVo> list;
        try {
            if (params.getPage() == null) {
                params.setPage(1);
            }
            if (params.getSize() == null) {
                params.setSize(10);
            }
            if (params.getSectionId() == null) {
                return new ResponseData<>(0, ArticleConstants.PARAMS_NOT_NULL, "");
            }
            list = articleContentService.appArticleList(params);
        } catch (Exception e) {
            _log.error("APP端查看文章列表查询失败", e);
            return new ResponseData<>(0, "查询失败", "");
        }
        return new ResponseData<>(list);
    }

    @GetMapping(value = "/{articleId}")
    @ApiOperation(value = "APP端查看文章详情", notes = "APP端查看文章详情", httpMethod = "GET")
    public ResponseData<AppArticleDetailsVo> appArticleDetail(HttpServletRequest request, @PathVariable(name = "articleId") Long articleId) {
        AppArticleDetailsVo data;
        Long ssoid = null;
        try {
            // 判断游客还是登陆用户
            TokenAppUserInfo userInfo = getAppUserInfo(request);
            ssoid = userInfo != null ? userInfo.getAccountId() : null;
        } catch (Exception e) {
            _log.debug("文章详情用户未登录");
        }
        try {
            data = articleContentService.appArticleDetail(articleId, ssoid);
        } catch (Exception e) {
            _log.error("APP端查看文章详情失败", e);
            return new ResponseData<>(0, "查询失败", "");
        }
        return new ResponseData<>(data);
    }

    @PostMapping(value = "/rand")
    @ApiOperation(value = "APP端文章推荐列表", notes = "APP端文章推荐列表", httpMethod = "POST")
    public ResponseData<List<AppArticleListVo>> appArticleRand(@RequestBody AppArticleRandParams params) {
        List<AppArticleListVo> list;
        try {
            if (params.getArticleId() == null || params.getSectionId() == null) {
                return new ResponseData<>(0, ArticleConstants.PARAMS_NOT_NULL, "");
            }
            list = articleContentService.appArticleRand(params);
        } catch (Exception e) {
            _log.error("APP端文章推荐列表查询失败", e);
            return new ResponseData<>(0, "查询失败", "");
        }
        return new ResponseData<>(list);
    }

    @PostMapping(value = "/like")
    @ApiOperation(value = "APP端文章点赞", notes = "APP端文章点赞", httpMethod = "POST")
    public ResponseData<Object> like(HttpServletRequest request, @RequestBody AppArticleLikeParams params) {
        try {
            if (params.getArticleId() == null) {
                return new ResponseData<>(0, ArticleConstants.PARAMS_NOT_NULL, "");
            }
            TokenAppUserInfo info = getAppUserInfo(request);
            if (info == null || info.getAccountId() == null) {
                return new ResponseData<>(0, "用户信息为空", "");
            }
            params.setSsoid(info.getAccountId());
            articleLikeService.like(params);
        } catch (Exception e) {
            _log.error("APP端文章点赞失败", e);
            return new ResponseData<>(0, "操作失败", "");
        }
        return new ResponseData<>("");
    }

    @PostMapping(value = "/cancel/like")
    @ApiOperation(value = "APP端文章取消点赞", notes = "APP端文章取消点赞", httpMethod = "POST")
    public ResponseData<Object> cancelLike(HttpServletRequest request, @RequestBody AppArticleLikeParams params) {
        try {
            if (params.getArticleId() == null) {
                return new ResponseData<>(0, ArticleConstants.PARAMS_NOT_NULL, "");
            }
            TokenAppUserInfo info = getAppUserInfo(request);
            if (info == null || info.getAccountId() == null) {
                return new ResponseData<>(0, "用户信息为空", "");
            }
            params.setSsoid(info.getAccountId());
            articleLikeService.cancelLike(params);
        } catch (Exception e) {
            _log.error("APP端文章取消点赞失败", e);
            return new ResponseData<>(0, "操作失败", "");
        }
        return new ResponseData<>("");
    }

    @GetMapping(value = "/section/list")
    @ApiOperation(value = "APP端查看版块列表", notes = "APP端查看版块列表", httpMethod = "GET")
    public ResponseData<List<ArticleSectionVo>> appSectionList(HttpServletRequest request) {
        List<ArticleSectionVo> list;
        try {
            list = articleSectionService.findSectionList();
        } catch (Exception e) {
            _log.error("APP端查询版块列表失败", e);
            return new ResponseData<>(0, "查询失败", "failed");
        }
        return new ResponseData<>(list);
    }

}
