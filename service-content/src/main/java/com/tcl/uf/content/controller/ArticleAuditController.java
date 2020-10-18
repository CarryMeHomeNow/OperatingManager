package com.tcl.uf.content.controller;

import com.alibaba.fastjson.JSON;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.common.base.util.pageutiles.PageUtils;
import com.tcl.uf.content.consts.ResponseObjectConstans;
import com.tcl.uf.content.dto.ArticleAuditParams;
import com.tcl.uf.content.dto.ArticleRejectDto;
import com.tcl.uf.content.service.ArticleAuditService;
import com.tcl.uf.content.vo.ArticleAuditVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author youyun.xu
 * @ClassName: ArticleContentController
 * @Description: 文章审核管理
 * @date 2020/7/27 上午19:00
 */
@Api(description = "文章审核管理")
@RestController
@RequestMapping("/article/audit")
public class ArticleAuditController {

    private static final Logger _log = LoggerFactory.getLogger(ArticleContentController.class);

    @Autowired
    private ArticleAuditService articleAuditService;

    @RequestMapping(value = "/back/find/list", method = RequestMethod.POST)
    @ApiOperation(value = "审核文章列表", notes = "审核文章列表", httpMethod = "POST")
    public ResponseData<ListWithPage<ArticleAuditVo>> findList(HttpServletRequest request, HttpServletResponse response, @RequestBody ArticleAuditParams articleAuditParams) {
        ListWithPage<ArticleAuditVo> list = null;
        try {
            Pageable pageable = PageUtils.getPageFromRequestParam(request);
            list = articleAuditService.findList(pageable, articleAuditParams);
        } catch (Exception e) {
            _log.error("查询审核文章列表发生异常 查询参数 {} 异常信息 {}", JSON.toJSON(articleAuditParams),e);
            return ResponseObjectConstans.AUDIT_FINDLIST_ERROR;
        }
        return new ResponseData<ListWithPage<ArticleAuditVo>>(list);
    }

    @RequestMapping(value = "/back/article/pass", method = RequestMethod.POST)
    @ApiOperation(value = "审核通过", notes = "审核通过", httpMethod = "POST")
    public ResponseData<Object> pass(HttpServletRequest request, HttpServletResponse response, @RequestBody List<Long> articleId) {
        try {
            articleAuditService.auditPass(articleId);
        } catch (Exception e) {
            _log.error("审核通过操作发生异常 请求参数 {} 异常信息 {}",articleId,e);
            return ResponseObjectConstans.AUDIT_PASS_ERROR;
        }
        return new ResponseData<Object>("操作成功");
    }

    @RequestMapping(value = "/back/article/reject", method = RequestMethod.POST)
    @ApiOperation(value = "审核驳回", notes = "审核驳回", httpMethod = "POST")
    public ResponseData<Object> reject(HttpServletRequest request, HttpServletResponse response, @RequestBody ArticleRejectDto articleRejectDto) {
        try {
            articleAuditService.auditReject(articleRejectDto);
        } catch (Exception e) {
            _log.error("驳回操作发生异常 请求参数 {} 异常信息 {}",JSON.toJSON(articleRejectDto),e);
            return ResponseObjectConstans.AUDIT_REJECT_ERROR;
        }
        return new ResponseData<Object>("操作成功");
    }
}
