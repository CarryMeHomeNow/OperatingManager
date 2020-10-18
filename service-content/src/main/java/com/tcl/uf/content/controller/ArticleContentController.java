package com.tcl.uf.content.controller;

import com.alibaba.fastjson.JSON;
import com.tcl.uf.common.base.dto.AbstractBaseController;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.dto.TokenData;
import com.tcl.uf.common.base.dto.token.TokenAppUserInfo;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.common.base.util.pageutiles.PageUtils;
import com.tcl.uf.content.consts.ResponseObjectConstans;
import com.tcl.uf.content.dto.ArticleContentDto;
import com.tcl.uf.content.dto.ArticleContentParams;
import com.tcl.uf.content.dto.ArticleTopDto;
import com.tcl.uf.content.service.ArticleContentService;
import com.tcl.uf.content.vo.ArticleContentDetailsVo;
import com.tcl.uf.content.vo.ArticleContentVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author youyun.xu
 * @ClassName: ArticleContentController
 * @Description: 文章内容管理
 * @date 2020/7/23 上午09:00
 */
@Api(description = "文章内容管理")
@RestController
@RequestMapping("/article/content")
public class ArticleContentController extends AbstractBaseController {

    private static final Logger _log = LoggerFactory.getLogger(ArticleContentController.class);

    @Autowired
    private ArticleContentService articleContentService;

    @RequestMapping(value = "/back/saveOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "新增或修改文章内容", notes = "新增或修改文章内容", httpMethod = "POST")
    public ResponseData<Object> saveOrUpdate(@RequestBody ArticleContentDto articleContentDto,HttpServletRequest request) {
        TokenData info = getManageUserInfo(request);
        Long articleId = null;
        try {
            articleId = articleContentService.saveOrUpdate(articleContentDto,info);
        } catch (Exception e) {
            _log.error("文章内容发生异常 请求参数 {} 异常信息 {}",JSON.toJSON(articleContentDto), e);
            return ResponseObjectConstans.SAVE_OR_UPDATE_ERROR;
        }
        return new ResponseData<Object>(articleId);
    }

    @RequestMapping(value = "/back/article/{articleId}", method = RequestMethod.GET)
    @ApiOperation(value = "查看文章详情", notes = "查看文章详情", httpMethod = "GET")
    public ResponseData<ArticleContentDetailsVo> findArticle(@PathVariable(name = "articleId") Long articleId) {
        ArticleContentDetailsVo articleContentDetailsVo = null;
        try {
            articleContentDetailsVo = articleContentService.findArticleContent(articleId);
        } catch (Exception e) {
            _log.error("查看文章详情发生异常 请求参数 {} 异常信息 {}",articleId, e);
        }
        return new ResponseData<ArticleContentDetailsVo>(articleContentDetailsVo);
    }

    @RequestMapping(value = "/back/find/list", method = RequestMethod.POST)
    @ApiOperation(value = "查看文章列表", notes = "查看文章列表", httpMethod = "POST")
    public ResponseData<ListWithPage<ArticleContentVo>> findList(HttpServletRequest request, HttpServletResponse response, @RequestBody ArticleContentParams articleContentParams) {
        ListWithPage<ArticleContentVo> list = null;
        try {
            Pageable pageable = PageUtils.getPageFromRequestParam(request);
            list = articleContentService.findList(pageable, articleContentParams);
        } catch (Exception e) {
            _log.error("查看文章列表发生异常 请求参数 {} 异常信息 {}",JSON.toJSON(articleContentParams), e);
        }
        return new ResponseData<ListWithPage<ArticleContentVo>>(list);
    }

    @RequestMapping(value = "/back/article/set/top", method = RequestMethod.POST)
    @ApiOperation(value = "文章置顶", notes = "文章置顶", httpMethod = "POST")
    public ResponseData<Object> articleTop(HttpServletRequest request, HttpServletResponse response, @RequestBody ArticleTopDto articleTopDto) {
        try {
            if(articleTopDto.getSection() == null || articleTopDto.getSection().isEmpty()){
                return ResponseObjectConstans.TOP_EMPTY_NOTICE;
            }
            articleContentService.articleTop(articleTopDto);
        } catch (Exception e) {
            _log.error("文章置顶发生异常 请求参数 {} 异常信息 {}",JSON.toJSON(articleTopDto), e);
            return ResponseObjectConstans.TOP_ERROR;
        }
        return new ResponseData<Object>("置顶成功");
    }

    @RequestMapping(value = "/back/article/cancel/top", method = RequestMethod.GET)
    @ApiOperation(value = "取消置顶", notes = "取消置顶", httpMethod = "GET")
    public ResponseData<Object> articleCancelTop(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "articleId") Long articleId) {
        try {
            articleContentService.cancelArticleTop(articleId);
        } catch (Exception e) {
            _log.error("取消置顶发生异常 请求参数 {} 异常信息 {}", articleId,e);
            return ResponseObjectConstans.ARTICLE_CANCELTOP_ERROR;
        }
        return new ResponseData<Object>("取消置顶成功");
    }

    @RequestMapping(value = "/back/article/delete", method = RequestMethod.POST)
    @ApiOperation(value = "删除文章(单个或批量)", notes = "删除文章(单个或批量)", httpMethod = "POST")
    public ResponseData<Object> delete(HttpServletRequest request, HttpServletResponse response, @RequestBody List<Long> articleIds) {
        try {
            articleContentService.delete(articleIds);
        } catch (Exception e) {
            _log.error("删除文章(单个或批量)发生异常 请求参数 {} 异常信息 {}",articleIds, e);
            return ResponseObjectConstans.ARTICLE_DELETE_ERROR;
        }
        return new ResponseData<Object>("文章删除成功");
    }

    @RequestMapping(value = "/back/article/offline", method = RequestMethod.POST)
    @ApiOperation(value = "文章下架(单个或批量)", notes = "文章下架(单个或批量)", httpMethod = "POST")
    public ResponseData<Object> offline(HttpServletRequest request, HttpServletResponse response, @RequestBody List<Long> articleIds) {
        try {
            articleContentService.offline(articleIds);
        } catch (Exception e) {
            _log.error("文章下架(单个或批量)发生异常 请求参数 {} 异常信息 {}", articleIds,e);
            return ResponseObjectConstans.ARTICLE_OFFLINE_ERROR;
        }
        return new ResponseData<Object>("文章下架成功");
    }

    @RequestMapping(value = "/back/article/publish", method = RequestMethod.POST)
    @ApiOperation(value = "文章发布(单个或批量)", notes = "文章发布(单个或批量)", httpMethod = "POST")
    public ResponseData<Object> publish(HttpServletRequest request, HttpServletResponse response, @RequestBody List<Long> articleIds) {
        try {
            articleContentService.publish(articleIds);
        } catch (Exception e) {
            _log.error("文章发布(单个或批量)发生异常 请求参数 {} 异常信息 {}",articleIds, e);
            return ResponseObjectConstans.ARTICLE_PUBLISH_ERROR;
        }
        return new ResponseData<Object>("文章发布成功");
    }

    @RequestMapping(value = "/back/update/browse/base", method = RequestMethod.GET)
    @ApiOperation(value = "修改浏览基数", notes = "修改浏览基数", httpMethod = "GET")
    public ResponseData<Object> updateBrowse(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "browseBase") Integer browseBase,@RequestParam(name = "articleId") Long articleId) {
        try {
            articleContentService.updateBrowse(browseBase,articleId);
        } catch (Exception e) {
            _log.error("修改浏览基数发生异常 文章编码{} 浏览基数{} 异常信息 {}", articleId,browseBase,e);
            return ResponseObjectConstans.UPDATE_BROWSE_ERROR;
        }
        return new ResponseData<Object>("修改基数成功");
    }

}
