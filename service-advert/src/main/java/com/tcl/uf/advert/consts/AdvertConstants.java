package com.tcl.uf.advert.consts;

/**
 * @author linhuimin@tcl.com
 * @ClassName: AdvertConstants
 * @Description:广告消息相关常量元素
 * @date 2020/8/3 17:00
 */
public class AdvertConstants {

    /*伪删除标记 0:无效 1:有效*/
    public static final int DELETE_FLAG_FALSE = 0;
    public static final int DELETE_FLAG_TRUE = 1;

    /*公用状态标识  0:否  1:是*/
    public static final int COMMON_STATUS_FALSE = 0;
    public static final int COMMON_STATUS_TRUE = 1;

    /*广告位状态   0:草稿 1:审核中 2:上线 3:下线*/
    public static final int AD_LOC_STATUS_DRAFT = 0;
    public static final int AD_LOC_STATUS_AUDITING = 1;
    public static final int AD_LOC_STATUS_ONLINE = 2;
    public static final int AD_LOC_STATUS_OFFLINE = 3;

    /*广告资源状态   0:草稿 1:审核中 2:上线 3:下线*/
    public static final int AD_RES_STATUS_DRAFT = 0;
    public static final int AD_RES_STATUS_AUDITING = 1;
    public static final int AD_RES_STATUS_ONLINE = 2;
    public static final int AD_RES_STATUS_OFFLINE = 3;

    /*审核状态  0:未审核 1:通过 2:驳回 */
    public static final int AUDIT_STATUS_DRAFT = 0;
    public static final int AUDIT_STATUS_PASS = 1;
    public static final int AUDIT_STATUS_REJECT = 2;

    /*广告用户状态  0:草稿 1:审核中 2:上线 3:下线*/
    public static final int ROLE_STATUS_DRAFT = 0;
    public static final int ROLE_STATUS_AUDITING = 1;
    public static final int ROLE_STATUS_ONLINE  = 2;
    public static final int ROLE_STATUS_OFFLINE= 3;

    /*页面列表类型  1:正在投放列表  2:已下线列表*/
    public static final int PAGE_LIST_TYPE_RESOURCE_OFFLINE = 1;
    public static final int PAGE_LIST_TYPE_RESOURCE_ONLINE = 2;

    /*用户部门类型  1:业务方部门  2:管理部门*/
    public static final int DEPARTMENT_TYPE_BUSINESS = 1;
    public static final int DEPARTMENT_TYPE_MANAGEMENT = 2;

    /*审核单类型 */
    public static final String AUDIT_TYPE_AD_LOC = "AD_LOC";
    public static final String AUDIT_TYPE_AD_RES = "AD_RES";
    public static final String AUDIT_TYPE_AD_USER = "AD_USER";

    /*广告用户角色*/
    public static final String ADVERT_ROLE_ADMIN = "ADVERT_ADMIN";
    public static final String ADVERT_ROLE_USER = "ADVERT_USER";

    /*广告管理超级管理员对应后台角色名*/
    public static final String MANAGEMENT_ROLE_ADMIN_NAME = "root";

    /*邮件内容模版*/
    public static final String MAIL_TEMPLATE_USER_AUDIT_TITLE = "广告投放平台权限变更通知";
    public static final String MAIL_TEMPLATE_USER_AUDIT_PASS = "你好, 办公网址{url}的权限已开通，请查看对应的投放文档";
    public static final String MAIL_TEMPLATE_USER_AUDIT_REJECT = "你好, 办公网址{url}的权限已驳回，驳回原因:{remark}";
    public static final String MAIL_TEMPLATE_USER_STATUS_OFFLINE = "你好, 办公网址{url}的权限已被取消，有疑问请联系管理员";
    public static final String MAIL_TEMPLATE_USER_STATUS_ONLINE= "你好, 办公网址{url}的权限已被启用，请查看对应的投放文档";
    public static final String MAIL_TEMPLATE_RESOURCE_AUDIT_TITLE = "广告投放平台素材审核通知";
    public static final String MAIL_TEMPLATE_RESOURCE_AUDIT_PASS = "你好, 您在广告投放平台填报的投放素材:{title} 投放日期：{effectiveDate} 已审核通过，请查及时安排上线";
    public static final String MAIL_TEMPLATE_RESOURCE_AUDIT_REJECT = "你好, 您在广告投放平台填报的投放素材:{title} 投放日期：{effectiveDate} 已驳回，驳回原因:{remark}";

    /*管理后台地址*/
    public static final String ADVERT_SYSTEM_URL = "https://www.tcl.com";

    /*广告缓存标识*/
    public static final String REDIS_NAMESPACE_LOCATION_RESOURCE = "tclPlus:advert:location:resource";

    public static final String REDIS_NAMESPACE_LOCATION_RESOURCE_SEQNO = "tclPlus:advert:location:resource:seqNo";

    /*未授权状态码  101未授权 */
    public static final int AUTH_STATUS_CODE_UNAUTH = 101;

    /*广告资源重复填报状态码  201 */
    public static final int RESOURCE_STATUS_CODE_EXISTS = 201;


    /*广告位类型 */

    /*广告资源类型 */

}
