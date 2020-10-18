package com.tcl.uf.activity.consts;

public class MenberAvtivityConstants {

    /*问卷状态 1:草稿 2:发布*/
    public static final String QUESTIONNAIRE_STATUS_DRAFT="1";
    public static final String GROUP_STATUS_PROCESS_ONLINE="2";
    public static final String GROUP_STATUS_PROCESS_OFFLINE="3";


    /*竞价英雄 开团 0:草稿 1:有效*/
    public static final String BIDDING_HERO_STATUS_DRAFT="0";
    public static final String BIDDING_HERO_STATUS_EFFECTIVE="1";


    /*伪删除标记 0:无效 1:有效*/
    public static final Integer DELETE_FLAG_INVALID=0;
    public static final Integer DELETE_FLAG_EFFECTIVE=1;

    /*开启状态 0:无效 1:有效*/
    public static final String DELETE_FLAG_NOT_START="false";
    public static final String BIDDING_HERO_PROCESSING="true";

    /*出价记录状态 0:已出价 1:成功 2:失败*/
    public static final String USER_DID_PROCESSING="0";
    public static final String USER_DID_SUCCESS="1";
    public static final String USER_DID_FAILURE="2";

    /*开团方式（1:自动、0:手动）*/
    public static final String OPEN_GRUOP_MANUAL="0";
    public static final String OPEN_GRUOP_AUTO="1";

    /*竞猜状态（1:失败、0:进行中 2 成功）*/
    public static final String GUESS_USER_STATUS_BEGIN="0";
    public static final String GUESS_USER_STATUS_FAILURE="1";
    public static final String GUESS_USER_STATUS_SUCCESS="2";

    /*抽奖机会是否使用[0:未使用 1:已使用]*/
    public static final String LOTTERY_CHANCE_NOT_USE="0";
    public static final String LOTTERY_CHANCE_USE="1";

    /*抽奖机会是否有效[0:无效 1:有效]*/
    public static final String LOTTERY_CHANCE_ACTIVE_STATE_Y="1";
    public static final String LOTTERY_CHANCE_ACTIVE_STATE_N="0";

    /*抽奖机会是否使用[0:未使用 1:已使用]*/
    public static final String LOTTERY_CHANCE_DEFAULT="0";
    public static final String LOTTERY_CHANCE_QUESTIONNAIRE="1";

    /*抽奖机来源[ 0:每日系统自动发放 1:问卷 2:每日赠送 3:九宫格中奖 4:大屏活动 5:天天抽锦鲤 6:分享获得]*/
    public static final String LOTTERY_CHANCE_SOURCE_1="1";
    public static final String LOTTERY_CHANCE_SOURCE_2="2";
    public static final String LOTTERY_CHANCE_SOURCE_3="3";
    public static final String LOTTERY_CHANCE_SOURCE_4="4";
    public static final String LOTTERY_CHANCE_SOURCE_5="5";
    public static final String LOTTERY_CHANCE_SOURCE_6="6";

    /*团完成状态 0:进行中 1:成功 2:失败*/
    public static final String GROUP_DID_BEGIN="0";
    public static final String GROUP_DID_SUCCESS="1";
    public static final String GROUP_DID_FAILURE="2";

    /*1 实物 2 金币 3 积分 4 优惠券 5 兑换券 6 谢谢参与 7 赠送次数 8 现金*/
    public static final String LOTTERY_TYPE_1="1";
    public static final String LOTTERY_TYPE_2="2";
    public static final String LOTTERY_TYPE_3="3";
    public static final String LOTTERY_TYPE_4="4";
    public static final String LOTTERY_TYPE_5="5";
    public static final String LOTTERY_TYPE_6="6";
    public static final String LOTTERY_TYPE_7="7";
    public static final String LOTTERY_TYPE_8="8";
    
    // 转盘类型
    public static final String LOTTERY_TYPE_CJ0001="CJ0001"; // 每日抽奖-TCL会员小程序每日抽奖
    public static final String LOTTERY_TYPE_CJ0002="CJ0002"; // 元旦抽奖-微信H5抽奖
    public static final String LOTTERY_TYPE_CJ0003="CJ0003"; // 五一抽奖-微信H5抽奖
    public static final String LOTTERY_TYPE_CJ0004="CJ0004"; // 天天抽锦鲤-微信H5抽奖

    /* 金币：超市赢家 每日抽奖 问卷有礼*/
    public static final String SOURCE_SUPERMARKET_WINNER="SOURCE_SUPERMARKET_WINNER";   //超市赢家
    public static final String SOURCE_EVERYDAY_LOTTERY_TASK="SOURCE_EVERYDAY_LOTTERY_TASK";   //每日抽奖[任务]
    public static final String SOURCE_EVERYDAY_LOTTERY_WIN="SOURCE_EVERYDAY_LOTTERY_WIN";   //每日抽奖[中奖]
    public static final String SOURCE_QUESTIONNAIRE="SOURCE_QUESTIONNAIRE";  //问卷有礼
    public static final String SOURCE_BIDDING_HERO_TASK="SOURCE_BIDDING_HERO_TASK";//竞价英雄[任务]
    public static final String SOURCE_EVERYDAY_LOTTERY_CONSUME="SOURCE_EVERYDAY_LOTTERY_CONSUME";//每日抽奖[消耗金币]


    /* 积分：超市赢家 每日抽奖 问卷有礼*/
    public static final String POINT_LOTTERY="POINT_LOTTERY";
    // 抽中现金
    public static final String MONEY_LOTTERY_MATCH = "LOTTERY_MATCH";

    /* 问卷有礼*/
    public static final String  QUESTIONNAIRE_TYPE_O="1";

    /*活动创建来源**/
    public static final String CREATE_ACTIVITY_SOURCE_520="1";
    public static final String CREATE_ACTIVITY_SOURCE_618="2";
    public static final String CREATE_ACTIVITY_SOURCE_OCN="3";

}
