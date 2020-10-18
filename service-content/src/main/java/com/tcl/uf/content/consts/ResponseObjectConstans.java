package com.tcl.uf.content.consts;

import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.content.vo.ArticleAuditVo;
import com.tcl.uf.content.vo.ArticleSectionVo;

/**
 * @author youyun.xu
 * @ClassName: ResponseObjectConstans
 * @Description: 文章内容错误响应管理
 * @date 2020/9/3 16:31
 */
public class ResponseObjectConstans {

    //文章相关
    public final static ResponseData<Object> SAVE_OR_UPDATE_ERROR = new ResponseData<>(0, "新增或保存文章失败", "");
    public final static ResponseData<ListWithPage<ArticleAuditVo>> AUDIT_FINDLIST_ERROR = new ResponseData<>(0, "查询文章待审列表失败", "");
    public final static ResponseData<Object> AUDIT_PASS_ERROR = new ResponseData<>(0, "审核通过失败", "");
    public final static ResponseData<Object> AUDIT_REJECT_ERROR = new ResponseData<>(0, "审核驳回失败", "");
    public final static ResponseData<Object> TOP_ERROR = new ResponseData<>(0, "文章置顶失败", "");
    public final static ResponseData<Object> ARTICLE_CANCELTOP_ERROR = new ResponseData<>(0, "取消文章置顶失败", "");
    public final static ResponseData<Object> ARTICLE_DELETE_ERROR = new ResponseData<>(0, "文章删除失败", "");
    public final static ResponseData<Object> ARTICLE_OFFLINE_ERROR = new ResponseData<>(0, "文章下线失败", "");
    public final static ResponseData<Object> ARTICLE_PUBLISH_ERROR = new ResponseData<>(0, "文章发布失败", "");
    public final static ResponseData<Object> UPDATE_BROWSE_ERROR = new ResponseData<>(0, "修改浏览基数失败", "");
    public final static ResponseData<Object> TOP_EMPTY_NOTICE = new ResponseData<>(0, "至少选择一个置顶版块", "");

    //版块相关
    public final static ResponseData<ArticleSectionVo> SECTION_SAVE_ERROR = new ResponseData<>(0, "保存失败", "");
    public final static ResponseData<Object> SECTION_DELETE_ERROR = new ResponseData<>(0, "删除", "");
    public final static ResponseData<Object> POSITION_UPDATE_ERROR = new ResponseData<>(0, "位置更新失败", "");
    public final static ResponseData<ArticleSectionVo> TITLE_NULL_NOTICE = new ResponseData<>(0, "版块标题或子标题不能为空", "");
}
