package com.tcl.uf.common.base.util.tangram.vo;

import java.util.List;

public class TangramItemVo {

    // 通用参数
    private String type;// item的类型
    private String category;// 商品或优惠券的种类
    private TangramStyleVo style;// item样式默认不填
    private ActionParams actionParams;// 通用参数 执行点击cell操作时需要带的参数
    private String cornerRadius;// 通用参数 整个控件四周的圆角

    // text参数
    private String textContent;// 文本内容
    private String textAlignment;// 文本对齐方式
    private String textColor;// 文本颜色
    private Integer fontSize;// 文本字号
    private Integer lineSpace;// 行间距
    private List<RichText> richText;// 富文本列表

    // image参数
    private String imgUrl;// 网络图片地址
    private String imgPlaceHolder;// 占位图
    private String imgWidth;// 图片的宽度
    private String imgHeight;// 图片的高度
    // video参数
    private String videoUrl;// 视频的地址
    private SupportVo supports;// 支持的功能，包括progress（显示进度条），fullScreen（支持全屏）


    // 商品组件类型
    private String commodityType;
    private ImgParams imgParams;// 图片参数
    private TextParams titleParams;// 标题的参数
    private TextParams subtitleParams;// 副标题的参数
    private TextParams currentPriceParams;// 折后价格（当前价格）
    private TextParams originalPriceParams;// 原价（删除线内的价格）
    private List<TipVo> tips;// 数组，表示促销活动

    // COUPON 优惠券
    private Integer statusType; // 优惠券状态 1：可领取，按钮显示领取   2：可兑换：按钮显示兑换   3：已领取：按钮显示去使用   4：已使用、已失效：不显示按钮
    private TextParams statusTipParams;// 优惠券领取/兑换提示
    private TextParams moneyParams;// 优惠券的价值/金额
    private TextParams limitParams;// 优惠券的限制条件
    private TextParams rangeParams;// 适用范围
    private TextParams expireTimeParams;// 过期时间 必填
    private Boolean redPointStatus;// 红点提醒（新领取的券） 默认false

    public static class RichText {
        private String richType;// 富文本的类型 color（颜色），link（链接），font（字体）
        private String range;// 富文本内容的范围，包含两个值，第一个元素是起始点，第二个元素是长度
        private String textColor;// 富文本内容的颜色
        private String linkUrl;// 链接的url
        private Integer fontSize;// 字体大小
        private String bold;// 字体加粗，值为true时，加粗当前范围的内容加粗显示
        private String textBgColor;// 字体背景颜色

        public String getRichType() {
            return richType;
        }

        public void setRichType(String richType) {
            this.richType = richType;
        }

        public String getRange() {
            return range;
        }

        public void setRange(String range) {
            this.range = range;
        }

        public String getTextColor() {
            return textColor;
        }

        public void setTextColor(String textColor) {
            this.textColor = textColor;
        }

        public String getLinkUrl() {
            return linkUrl;
        }

        public void setLinkUrl(String linkUrl) {
            this.linkUrl = linkUrl;
        }

        public Integer getFontSize() {
            return fontSize;
        }

        public void setFontSize(Integer fontSize) {
            this.fontSize = fontSize;
        }

        public String getBold() {
            return bold;
        }

        public void setBold(String bold) {
            this.bold = bold;
        }

        public String getTextBgColor() {
            return textBgColor;
        }

        public void setTextBgColor(String textBgColor) {
            this.textBgColor = textBgColor;
        }
    }

    public static class ActionParams {
        private String actionType;// 点击组件触发的事件类型
        private String actionUrl;// actionType对应的url
        private String couponId;// 优惠券ID

        public String getActionType() {
            return actionType;
        }

        public void setActionType(String actionType) {
            this.actionType = actionType;
        }

        public String getActionUrl() {
            return actionUrl;
        }

        public void setActionUrl(String actionUrl) {
            this.actionUrl = actionUrl;
        }

        public String getCouponId() {
            return couponId;
        }

        public void setCouponId(String couponId) {
            this.couponId = couponId;
        }
    }

    public static class ImgParams {
        private String imgUrl;// 网络图片地址
        private String imgPlaceHolder;// 占位图，对应的是本地图片
        private String imgWidth;// 图片的宽度
        private String imgHeight;// 图片的高度
        private List<String> imgEdgeInsets;// 图片边距，内部有四个值，分别代表上左下右
        private String smallIcon;// 商品左上角的图标的url地址

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getImgPlaceHolder() {
            return imgPlaceHolder;
        }

        public void setImgPlaceHolder(String imgPlaceHolder) {
            this.imgPlaceHolder = imgPlaceHolder;
        }

        public String getImgWidth() {
            return imgWidth;
        }

        public void setImgWidth(String imgWidth) {
            this.imgWidth = imgWidth;
        }

        public String getImgHeight() {
            return imgHeight;
        }

        public void setImgHeight(String imgHeight) {
            this.imgHeight = imgHeight;
        }

        public List<String> getImgEdgeInsets() {
            return imgEdgeInsets;
        }

        public void setImgEdgeInsets(List<String> imgEdgeInsets) {
            this.imgEdgeInsets = imgEdgeInsets;
        }

        public String getSmallIcon() {
            return smallIcon;
        }

        public void setSmallIcon(String smallIcon) {
            this.smallIcon = smallIcon;
        }
    }

    public static class TextParams {
        private String textContent;
        private String textPosition;
        private String textAlignment;
        private String textColor;
        private Integer fontSize;
        private String lineSpace;
        private List<String> imgEdgeInsets;
        private List<RichText> richText;

        public String getTextContent() {
            return textContent;
        }

        public void setTextContent(String textContent) {
            this.textContent = textContent;
        }

        public String getTextPosition() {
            return textPosition;
        }

        public void setTextPosition(String textPosition) {
            this.textPosition = textPosition;
        }

        public String getTextAlignment() {
            return textAlignment;
        }

        public void setTextAlignment(String textAlignment) {
            this.textAlignment = textAlignment;
        }

        public String getTextColor() {
            return textColor;
        }

        public void setTextColor(String textColor) {
            this.textColor = textColor;
        }

        public Integer getFontSize() {
            return fontSize;
        }

        public void setFontSize(Integer fontSize) {
            this.fontSize = fontSize;
        }

        public String getLineSpace() {
            return lineSpace;
        }

        public void setLineSpace(String lineSpace) {
            this.lineSpace = lineSpace;
        }

        public List<String> getImgEdgeInsets() {
            return imgEdgeInsets;
        }

        public void setImgEdgeInsets(List<String> imgEdgeInsets) {
            this.imgEdgeInsets = imgEdgeInsets;
        }

        public List<RichText> getRichText() {
            return richText;
        }

        public void setRichText(List<RichText> richText) {
            this.richText = richText;
        }
    }

    public static class TipVo {
        private String tip; // 促销名称
        private String tipColor;// 背景颜色（文字颜色是白色）
        private String fontSize;// 字号

        public String getTip() {
            return tip;
        }

        public void setTip(String tip) {
            this.tip = tip;
        }

        public String getTipColor() {
            return tipColor;
        }

        public void setTipColor(String tipColor) {
            this.tipColor = tipColor;
        }

        public String getFontSize() {
            return fontSize;
        }

        public void setFontSize(String fontSize) {
            this.fontSize = fontSize;
        }
    }

    public static class SupportVo {
        private Boolean progress;// 显示进度条
        private Boolean fullScreen;// 支持全屏
        private Boolean gesture;

        public SupportVo() {
            this.progress = true;
            this.fullScreen = true;
        }

        public Boolean getProgress() {
            return progress;
        }

        public void setProgress(Boolean progress) {
            this.progress = progress;
        }

        public Boolean getFullScreen() {
            return fullScreen;
        }

        public void setFullScreen(Boolean fullScreen) {
            this.fullScreen = fullScreen;
        }

        public Boolean getGesture() {
            return gesture;
        }

        public void setGesture(Boolean gesture) {
            this.gesture = gesture;
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TangramStyleVo getStyle() {
        return style;
    }

    public void setStyle(TangramStyleVo style) {
        this.style = style;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getTextAlignment() {
        return textAlignment;
    }

    public void setTextAlignment(String textAlignment) {
        this.textAlignment = textAlignment;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public Integer getFontSize() {
        return fontSize;
    }

    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }

    public Integer getLineSpace() {
        return lineSpace;
    }

    public void setLineSpace(Integer lineSpace) {
        this.lineSpace = lineSpace;
    }

    public List<RichText> getRichText() {
        return richText;
    }

    public void setRichText(List<RichText> richText) {
        this.richText = richText;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgPlaceHolder() {
        return imgPlaceHolder;
    }

    public void setImgPlaceHolder(String imgPlaceHolder) {
        this.imgPlaceHolder = imgPlaceHolder;
    }

    public String getImgWidth() {
        return imgWidth;
    }

    public void setImgWidth(String imgWidth) {
        this.imgWidth = imgWidth;
    }

    public String getImgHeight() {
        return imgHeight;
    }

    public void setImgHeight(String imgHeight) {
        this.imgHeight = imgHeight;
    }

    public ActionParams getActionParams() {
        return actionParams;
    }

    public void setActionParams(ActionParams actionParams) {
        this.actionParams = actionParams;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(String cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    public String getCommodityType() {
        return commodityType;
    }

    public void setCommodityType(String commodityType) {
        this.commodityType = commodityType;
    }

    public ImgParams getImgParams() {
        return imgParams;
    }

    public void setImgParams(ImgParams imgParams) {
        this.imgParams = imgParams;
    }

    public TextParams getTitleParams() {
        return titleParams;
    }

    public void setTitleParams(TextParams titleParams) {
        this.titleParams = titleParams;
    }

    public TextParams getSubtitleParams() {
        return subtitleParams;
    }

    public void setSubtitleParams(TextParams subtitleParams) {
        this.subtitleParams = subtitleParams;
    }

    public TextParams getCurrentPriceParams() {
        return currentPriceParams;
    }

    public void setCurrentPriceParams(TextParams currentPriceParams) {
        this.currentPriceParams = currentPriceParams;
    }

    public TextParams getOriginalPriceParams() {
        return originalPriceParams;
    }

    public void setOriginalPriceParams(TextParams originalPriceParams) {
        this.originalPriceParams = originalPriceParams;
    }

    public List<TipVo> getTips() {
        return tips;
    }

    public void setTips(List<TipVo> tips) {
        this.tips = tips;
    }

    public Integer getStatusType() {
        return statusType;
    }

    public void setStatusType(Integer statusType) {
        this.statusType = statusType;
    }

    public TextParams getStatusTipParams() {
        return statusTipParams;
    }

    public void setStatusTipParams(TextParams statusTipParams) {
        this.statusTipParams = statusTipParams;
    }

    public TextParams getMoneyParams() {
        return moneyParams;
    }

    public void setMoneyParams(TextParams moneyParams) {
        this.moneyParams = moneyParams;
    }

    public TextParams getLimitParams() {
        return limitParams;
    }

    public void setLimitParams(TextParams limitParams) {
        this.limitParams = limitParams;
    }

    public TextParams getRangeParams() {
        return rangeParams;
    }

    public void setRangeParams(TextParams rangeParams) {
        this.rangeParams = rangeParams;
    }

    public TextParams getExpireTimeParams() {
        return expireTimeParams;
    }

    public void setExpireTimeParams(TextParams expireTimeParams) {
        this.expireTimeParams = expireTimeParams;
    }

    public Boolean getRedPointStatus() {
        return redPointStatus;
    }

    public void setRedPointStatus(Boolean redPointStatus) {
        this.redPointStatus = redPointStatus;
    }

    public SupportVo getSupports() {
        return supports;
    }

    public void setSupports(SupportVo supports) {
        this.supports = supports;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
