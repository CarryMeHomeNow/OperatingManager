package com.tcl.uf.points.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tcl.uf.common.base.dto.token.TokenAppUserInfo;
import com.tcl.uf.common.base.ex.JMException;
import com.tcl.uf.points.vo.taskcomplete.PointCompleteBindWechat;
import com.tcl.uf.points.vo.taskcomplete.PointCompleteFeedback;
import com.tcl.uf.points.vo.taskcomplete.PointCompleteOpenAppMsgPush;
import com.tcl.uf.points.vo.taskcomplete.PointCompleteSendInviteFirendLink;
import com.tcl.uf.points.vo.taskcomplete.PointCompleteSharePage;
import com.tcl.uf.points.vo.taskcomplete.PointCompleteShopBuy;
import com.tcl.uf.points.vo.taskcomplete.TaskComplete;
import com.tcl.uf.points.vo.taskcomplete.TaskCompleteType;



/**
 * 积分任务完成service
 */
@Service
public class TaskCompleteService {
	
	//绑定微信
	@Autowired 
    private PointCompleteBindWechat pointCompleteBindWechat;
	//意见反馈
    @Autowired
    private PointCompleteFeedback pointCompleteFeedback;
    //打开消息推送
    @Autowired
    private PointCompleteOpenAppMsgPush pointCompleteOpenAppMsgPush;
    //发送好友邀请链接
    @Autowired
    private PointCompleteSendInviteFirendLink pointCompleteSendInviteFirendLink;
    //分享页面实现
    @Autowired
    private PointCompleteSharePage pointCompleteSharePage;
    //商城购物
    @Autowired
    private PointCompleteShopBuy pointCompleteShopBuy;

    public void complete(Integer type,TokenAppUserInfo user)throws JMException{
        TaskComplete t = null;
        String source = null;
        switch (type){
            case TaskCompleteType.SHARE_PAGE: //分享页面
               t = pointCompleteSharePage;
               source = "APP/小程序";
               break;
            case TaskCompleteType.BIND_WECHAT://绑定微信
                t = pointCompleteBindWechat;
                source = "账户系统";
                break;
            case TaskCompleteType.SHOP_BUY: //商城购物
                t = pointCompleteShopBuy;
                source = "商城订单系统";
                break;
            case TaskCompleteType.OPEN_MSG_PUSH: //打开消息推送
                t = pointCompleteOpenAppMsgPush;
                source = "APP";
                break;
            case TaskCompleteType.SEND_INVITE_LINK://发送好友邀请链接
                t = pointCompleteSendInviteFirendLink;
                source = "APP/小程序";
                break;
            case TaskCompleteType.FEED_BACK://意见反馈
                t = pointCompleteFeedback;
                source = "APP";
                break;
            default:
                throw new JMException("未找到对应任务");
        }
        t.taskEffective(type); 
        t.check(user,type);
        t.finish(user,type,source);
    }
    
}
