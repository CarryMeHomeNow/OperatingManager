package com.tcl.uf.content.consts;

/**
 * @author youyun.xu
 * @ClassName: ArticleConstants
 * @Description: 文章相关常量元素
 * @date 2020/7/27 14:23
 */
public class ArticleConstants {

    private ArticleConstants() {
        throw new UnsupportedOperationException("不可创建");
    }

    /*伪删除标记 0:无效 1:有效*/
    public static final Integer DELETE_FLAG_INVALID = 0;
    public static final Integer DELETE_FLAG_EFFECTIVE = 1;

    //置顶状态
    public static final  String TOP_STATUS_YES = "1";
    public static final  String TOP_STATUS_NO = "0";

    //文章审核状态[待审核、审核通过、驳回]
    public static final  String AUDIT_STATUS_WAIT = "1";
    public static final  String AUDIT_STATUS_PASS = "2";
    public static final  String AUDIT_STATUS_REJECT = "3";

    //文章状态(未发布、审核中、已发布、已驳回)
    public static final  String CONTENT_STATUS_UNPUBLISH = "0";
    public static final  String CONTENT_STATUS_AUDIT_WAIT = "1";
    public static final  String CONTENT_STATUS_PUBLISH = "2";
    public static final  String CONTENT_STATUS_AUDIT_REJECT = "3";

    public static final String REDIS_NAMESPACE_SECTION_RELATION = "tclPlus:content:section:relation_";
    public static final String REDIS_NAMESPACE_ARTICLE = "tclPlus:content:article";
    public static final String REDIS_NAMESPACE_SECTION_LIST = "tclPlus:content:section:list";
    public static final String REDIS_NAMESPACE_ARTICLE_APP_LIST = "tclPlus:content:article:app_list_";
    // 浏览数
    public static final String REDIS_NAMESPACE_ARTICLE_BROWSE = REDIS_NAMESPACE_ARTICLE + ":browse";

    // 文章点赞状态 1 点赞； 0 取消点赞
    public static final int STATUS_LIKE = 1;
    public static final int STATUS_NOT_LIKE = 0;

    public static final String PARAMS_NOT_NULL = "入参不能为空";
}
