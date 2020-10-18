package com.tcl.uf.common.base.util.tangram;

import com.alibaba.fastjson.JSON;
import com.tcl.uf.common.base.util.tangram.enums.ActionTypeEnum;
import com.tcl.uf.common.base.util.tangram.enums.ImgTypeStyleEnum;
import com.tcl.uf.common.base.util.tangram.enums.ItemTypeEnum;
import com.tcl.uf.common.base.util.tangram.enums.RichTextHtmlTagEnum;
import com.tcl.uf.common.base.util.tangram.enums.RichTextTypeEnum;
import com.tcl.uf.common.base.util.tangram.enums.TextTypeStyleEnum;
import com.tcl.uf.common.base.util.tangram.vo.TangramCartVo;
import com.tcl.uf.common.base.util.tangram.vo.TangramItemVo;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description tangram富文本转换工具类
 * @Author TangHuaJie
 * @Date Created in 2020/9/4 11:45
 */
public class TangramRichTextUtil {

    private static final Logger log = LoggerFactory.getLogger(TangramRichTextUtil.class);
    // 自定义标签：标签类型
    private static final String TANGGRAM_FEILD_CUSTOM_TYPE = "data-custom-type";
    // 自定义标签：参数的样式
    private static final String TANGGRAM_FEILD_CUSTOM_STYLE = "data-custom-style";
    // 自定义标签：商品或优惠券的种类
    private static final String TANGGRAM_FEILD_CUSTOM_CATEGORY = "data-custom-category";
    // 富文本内容
    private static final String TANGGRAM_FEILD_RICH_TEXT_CONTENT = "rich-text-content";

    public static String toTangramJson(String html) {
        log.info("handler html:{}", html);
        TangramCartVo tangramCartVo = new TangramCartVo();
        List<TangramItemVo> items = new ArrayList<>();
        Document doc = Jsoup.parse(html);
        Element bodyElement = doc.body();
        for (Node child : bodyElement.childNodes()) {
            if (child instanceof Element) {
                Element ele = (Element) child;
                TangramTransformHandler handler = new TangramTransformHandler();
                // 获取最外层节点属性值
                Map<String, String> itemAttr = getNodeAttr(ele);
                handler.setType(itemAttr.get(TANGGRAM_FEILD_CUSTOM_TYPE));
                // 根据HTML标签及自定义属性区别item类别
                if (ItemTypeEnum.COMMODITY_LIST.getValue().equals(handler.getType())) {
                    // commodityList类型item转化
                    handler.setCommodityType("list");
                    handler.setCategory(itemAttr.get(TANGGRAM_FEILD_CUSTOM_CATEGORY));
                    transformCommodity(ele, handler);
                } else if (ItemTypeEnum.COMMODITY_GRID.getValue().equals(handler.getType())) {
                    // commodityGrid类型item转化
                    handler.setCommodityType("grid");
                    handler.setCategory(itemAttr.get(TANGGRAM_FEILD_CUSTOM_CATEGORY));
                    transformCommodity(ele, handler);
                } else if (ItemTypeEnum.COUPON.getValue().equals(handler.getType())) {
                    // coupon类型item转化
                    handler.setCategory(itemAttr.get(TANGGRAM_FEILD_CUSTOM_CATEGORY));
                    transformCoupon(ele, handler);
                } else if (!ele.getElementsByTag(RichTextHtmlTagEnum.VIDEO.tag()).isEmpty()) {
                    // video类型item转化
                    transformVideo(ele, handler);
                } else if (ele.getElementsByTag(RichTextHtmlTagEnum.IMG.tag()).isEmpty()) {
                    // text类型item转化
                    transformText(ele, handler);

                } else if (!ele.getElementsByTag(RichTextHtmlTagEnum.IMG.tag()).isEmpty()) {
                    // image类型item转化
                    transformImg(ele, handler);
                }

                TangramItemVo itemVo = convertToItemVo(handler, itemAttr);
                items.add(itemVo);
            }
        }
        tangramCartVo.setItems(items);
        String json = JSON.toJSONString(tangramCartVo);
        log.info("transform result json:{}", json);
        return json;
    }

    /**
     * 文本转换
     *
     * @param ele  根节点
     * @param item 转换处理信息类
     */
    private static void transformText(Element ele, TangramTransformHandler item) {
        // 设置富文本textContent
        item.setTextContent(ele.text());
        item.setType(ItemTypeEnum.TEXT.getValue());
        item.setFirstNode(ele);
        for (Node nd : ele.childNodes()) {
            findChildNode(nd, item);
        }
    }

    /**
     * 图片转换
     *
     * @param ele  根节点
     * @param item 转换处理信息类
     */
    private static void transformImg(Element ele, TangramTransformHandler item) {
        item.setType(ItemTypeEnum.IMAGE.getValue());
        Elements elements = ele.getElementsByTag(RichTextHtmlTagEnum.IMG.tag());
        if (!elements.isEmpty()) {
            Element imgEle = elements.get(0);
            getStyleFromNode(imgEle, item);
            transformImgItem(item);
        }
    }

    /**
     * 视频转换
     *
     * @param ele  根节点
     * @param item 转换处理信息类
     */
    private static void transformVideo(Element ele, TangramTransformHandler item) {
        for (Node nd : ele.childNodes()) {
            findVideoChild(nd, item);
        }
        item.setType(ItemTypeEnum.VIDEO.getValue());
        // 设置默认控制参数
        item.setSupports(new TangramItemVo.SupportVo());
        item.setVideoUrl(item.getTagStyle().get(ImgTypeStyleEnum.IMG_URL.htmlStyleKey()));
    }

    /**
     * 优惠券转换
     *
     * @param ele  根节点
     * @param item 转换处理信息类
     */
    private static void transformCoupon(Element ele, TangramTransformHandler item) {
        String redPoint = ele.attr("data-red-point-status");
        if ("true".equals(redPoint)) {
            item.setRedPointStatus(Boolean.TRUE);
        }
        String couponId = ele.attr("data-coupon-id");
        TangramItemVo.ActionParams action = new TangramItemVo.ActionParams();
        action.setActionType(ActionTypeEnum.JUMP.getValue());
        action.setCouponId(couponId);
        item.setActionParams(action);
        // 第一层 data-custom-type="coupon"
        for (Element childEle : ele.children()) {
            // 第二层 data-custom-type="couponLeftInfo" 获取第三层各参数节点属性值
            childEle.children().forEach(c -> {
                Map<String, String> itemAttr = getNodeAttr(c);
                itemAttr.put(TANGGRAM_FEILD_RICH_TEXT_CONTENT, c.text());
                findSubRichTextChild(c, item);
                transformCouponItem(item, itemAttr);
            });

        }
    }

    /**
     * 商品转换
     *
     * @param ele  根节点
     * @param item 转换处理信息类
     */
    private static void transformCommodity(Element ele, TangramTransformHandler item) {
        for (Element childEle : ele.children()) {
            if ("goodsImgWrap".equals(childEle.attr(TANGGRAM_FEILD_CUSTOM_TYPE))) {
                // 商品图片参数处理 data-custom-type="goodsImgWrap"
                findVideoChild(childEle, item);
                transformImgItem(item);
                item.clearHandleInfo();
                item.clearTextIndex();
            } else if ("goodsInfoWrap".equals(childEle.attr(TANGGRAM_FEILD_CUSTOM_TYPE))) {
                // 商品信息参数处理 data-custom-type="goodsInfoWrap"
                childEle.children().forEach(c -> {
                    // 获取各参数节点属性值
                    Map<String, String> itemAttr = getNodeAttr(c);
                    itemAttr.put(TANGGRAM_FEILD_RICH_TEXT_CONTENT, c.text());
                    findSubRichTextChild(c, item);
                    transformCommodityItem(item, itemAttr);
                });
            }
        }
    }

    private static void findVideoChild(Node node, TangramTransformHandler item) {
        // 文本叶子节点
        if (node instanceof TextNode) {
            TextNode textNode = (TextNode) node;
            item.setTextContent(textNode.text());
            // html元素
        } else if (node instanceof Element) {
            Element ele = ((Element) node);
            item.getTagList().add(ele.tagName());
            // 获取元素标签属性
            getStyleFromNode(ele, item);
            ele.childNodes().forEach(nd -> findVideoChild(nd, item));
        }
    }

    /**
     * item转换
     *
     * @param item
     * @param itemAttr
     * @return
     */
    private static TangramItemVo convertToItemVo(TangramTransformHandler item, Map<String, String> itemAttr) {
        TangramItemVo itemVo = new TangramItemVo();
        if (item.getRichText().isEmpty()) {
            item.setRichText(null);
        }
        if (ItemTypeEnum.COMMODITY_LIST.getValue().equals(item.getType()) ||
                ItemTypeEnum.COMMODITY_GRID.getValue().equals(item.getType())) {
            itemVo.setType(item.getType());
            itemVo.setCommodityType(item.getCommodityType());
            itemVo.setCornerRadius(itemAttr.get(ImgTypeStyleEnum.CORNER_RADIUS.htmlStyleKey()));
            itemVo.setImgParams(item.getImgParams());
            itemVo.setTitleParams(item.getTitleParams());
            itemVo.setSubtitleParams(item.getSubtitleParams());
            itemVo.setCurrentPriceParams(item.getCurrentPriceParams());
            itemVo.setOriginalPriceParams(item.getOriginalPriceParams());
            itemVo.setTips(item.getTips());
            itemVo.setCategory(item.getCategory());
            // 设置跳转商品跳转链接
            itemVo.setActionParams(item.getActionParams());
        } else if (ItemTypeEnum.COUPON.getValue().equals(item.getType())) {
            itemVo.setType(ItemTypeEnum.COUPON.getValue());
            itemVo.setStatusType(item.getStatusType());
            itemVo.setStatusTipParams(item.getStatusTipParams());
            itemVo.setMoneyParams(item.getMoneyParams());
            itemVo.setLimitParams(item.getLimitParams());
            itemVo.setRangeParams(item.getRangeParams());
            itemVo.setExpireTimeParams(item.getExpireTimeParams());
            itemVo.setCategory(item.getCategory());
            // 设置跳转优惠券跳转链接
            itemVo.setActionParams(item.getActionParams());
        } else if (ItemTypeEnum.VIDEO.getValue().equals(item.getType())) {
            itemVo.setType(ItemTypeEnum.VIDEO.getValue());
            itemVo.setVideoUrl(item.getVideoUrl());
        } else if (ItemTypeEnum.TEXT.getValue().equals(item.getType())) {
            itemVo.setType(ItemTypeEnum.TEXT.getValue());
            itemVo.setTextContent(item.getTextContent());
            itemVo.setRichText(item.getRichText());
            itemVo.setFontSize(getFontSize(itemAttr));
            itemVo.setLineSpace(getLineSpace(itemAttr));
            itemVo.setTextColor(itemAttr.get(TextTypeStyleEnum.TEXT_COLOR.htmlStyleKey()));
            itemVo.setTextAlignment(itemAttr.get(TextTypeStyleEnum.TEXT_ALIGMENT.htmlStyleKey()));
        } else if (ItemTypeEnum.IMAGE.getValue().equals(item.getType())) {
            // 图片只有一个图片
            itemVo.setType(ItemTypeEnum.IMAGE.getValue());
            itemVo.setImgWidth(item.getImgParams().getImgWidth());
            itemVo.setImgHeight(item.getImgParams().getImgHeight());
            itemVo.setImgUrl(item.getImgParams().getImgUrl());
            itemVo.setActionParams(item.getActionParams());
        }
        return itemVo;
    }

    private static Integer getFontSize(Map<String, String> itemAttr) {
        try {
            return itemAttr.get(TextTypeStyleEnum.FONT_SIZE.htmlStyleKey()) == null ? null : Integer.parseInt(itemAttr.get(TextTypeStyleEnum.FONT_SIZE.htmlStyleKey()));
        } catch (Exception e) {
            log.error("富文本转换font-size格式错误：{}", itemAttr.get(TextTypeStyleEnum.FONT_SIZE.htmlStyleKey()), e);
        }
        return null;
    }

    private static Integer getLineSpace(Map<String, String> itemAttr) {
        try {
            return itemAttr.get(TextTypeStyleEnum.LINE_SPACE.htmlStyleKey()) == null ? null : Integer.parseInt(itemAttr.get(TextTypeStyleEnum.LINE_SPACE.htmlStyleKey()));
        } catch (Exception e) {
            log.error("富文本转换line-height格式错误：{}", itemAttr.get(TextTypeStyleEnum.LINE_SPACE.htmlStyleKey()), e);
        }
        return null;
    }

    private static int getCouponStatusType(String text) {
        if ("可领取".equals(text)) {
            return 1;
        } else if ("可兑换".equals(text)) {
            return 2;
        } else if ("已领取".equals(text)) {
            return 3;
        } else {
            return 4;
        }
    }

    /**
     * 优惠券参数转换
     *
     * @param item
     * @param itemAttr
     */
    private static void transformCouponItem(TangramTransformHandler item, Map<String, String> itemAttr) {
        String customType = itemAttr.get(TANGGRAM_FEILD_CUSTOM_TYPE);
        if ("statusParams".equals(customType)) {
            item.setStatusType(getCouponStatusType(itemAttr.get(TANGGRAM_FEILD_RICH_TEXT_CONTENT)));
        } else if ("statusTipParams".equals(customType)) {
            item.setStatusTipParams(convertToTextParams(item, itemAttr));
        } else if ("moneyParams".equals(customType)) {
            item.setMoneyParams(convertToTextParams(item, itemAttr));
        } else if ("limitParams".equals(customType)) {
            item.setLimitParams(convertToTextParams(item, itemAttr));
        } else if ("rangeParams".equals(customType)) {
            item.setRangeParams(convertToTextParams(item, itemAttr));
        } else if ("expireTimeParams".equals(customType)) {
            item.setExpireTimeParams(convertToTextParams(item, itemAttr));
        }
        item.clearHandleInfo();
        item.clearTextIndex();
        item.setRichText(new ArrayList<>());
    }

    /**
     * 转换文本参数
     *
     * @param item
     * @param itemAttr
     * @return
     */
    private static TangramItemVo.TextParams convertToTextParams(TangramTransformHandler item, Map<String, String> itemAttr) {
        TangramItemVo.TextParams titleParams = new TangramItemVo.TextParams();
        titleParams.setTextContent(itemAttr.get(TANGGRAM_FEILD_RICH_TEXT_CONTENT));
        titleParams.setRichText(item.hasRichText() ? item.getRichText() : null);
        if (StringUtils.isEmpty(itemAttr.get(TANGGRAM_FEILD_CUSTOM_STYLE))) {
            return titleParams;
        }
        Map<String, String> customStyleMap = new HashMap<>();
        String[] values = itemAttr.get(TANGGRAM_FEILD_CUSTOM_STYLE).split(";");
        for (String value : values) {
            String[] kv = value.split(":");
            if (kv.length != 2) {
                continue;
            }
            customStyleMap.put(kv[0], kv[1]);
        }
        titleParams.setLineSpace(customStyleMap.get(TextTypeStyleEnum.LINE_SPACE.htmlStyleKey()));
        titleParams.setTextColor(customStyleMap.get(TextTypeStyleEnum.TEXT_COLOR.htmlStyleKey()));
        titleParams.setTextAlignment(customStyleMap.get(TextTypeStyleEnum.TEXT_ALIGMENT.htmlStyleKey()));
        titleParams.setFontSize(getFontSize(customStyleMap));
        return titleParams;
    }

    /**
     * 商品参数转换
     *
     * @param item
     * @param itemAttr
     */
    private static void transformCommodityItem(TangramTransformHandler item, Map<String, String> itemAttr) {
        String customType = itemAttr.get(TANGGRAM_FEILD_CUSTOM_TYPE);
        if ("titleParams".equals(customType)) {
            item.setTitleParams(convertToTextParams(item, itemAttr));
        } else if ("subtitleParams".equals(customType)) {
            item.setSubtitleParams(convertToTextParams(item, itemAttr));
        } else if ("currentPriceParams".equals(customType)) {
            item.setCurrentPriceParams(convertToTextParams(item, itemAttr));
        } else if ("originalPriceParams".equals(customType)) {
            item.setOriginalPriceParams(convertToTextParams(item, itemAttr));
        } else if ("tips".equals(customType) || "tip".equals(customType)) {
            TangramItemVo.TipVo tip = new TangramItemVo.TipVo();
            tip.setFontSize(itemAttr.get(TextTypeStyleEnum.FONT_SIZE.htmlStyleKey()));
            tip.setTipColor(itemAttr.get(TextTypeStyleEnum.TEXT_COLOR.htmlStyleKey()));
            tip.setTip(itemAttr.get(TANGGRAM_FEILD_RICH_TEXT_CONTENT));
            if (item.getTips() != null) {
                item.getTips().add(tip);
            } else {
                List<TangramItemVo.TipVo> list = new ArrayList<>();
                list.add(tip);
                item.setTips(list);
            }
        }
        item.clearHandleInfo();
        item.clearTextIndex();
        item.setRichText(new ArrayList<>());
    }

    /**
     * 富文本参数转换
     *
     * @param item
     * @return
     */
    private static List<TangramItemVo.RichText> transformRichTextItem(TangramTransformHandler item) {
        List<TangramItemVo.RichText> richText = new ArrayList<>();
        if (item.getRichText() == null) {
            item.setRichText(new ArrayList<>());
        }
        // link类型富文本转换
        if (item.getTagList().contains(RichTextHtmlTagEnum.A.tag())) {
            TangramItemVo.RichText richT = new TangramItemVo.RichText();
            richT.setRichType(RichTextTypeEnum.LINK.getValue());
            richT.setLinkUrl(item.getTagStyle().get(TextTypeStyleEnum.LINK_URL.htmlStyleKey()));
            richT.setRange(item.getCurrentIndex() + "," + item.getTextLength());
            item.getRichText().add(richT);
            richText.add(richT);
        }
        // color类型富文本转换
        if (StringUtils.isNotEmpty(item.getTagStyle().get(TextTypeStyleEnum.TEXT_COLOR.htmlStyleKey()))
                || StringUtils.isNotEmpty(item.getTagStyle().get(TextTypeStyleEnum.BG_COLOR.htmlStyleKey()))) {
            TangramItemVo.RichText richT = new TangramItemVo.RichText();
            richT.setRichType(RichTextTypeEnum.COLOR.getValue());
            richT.setTextBgColor(item.getTagStyle().get(TextTypeStyleEnum.TEXT_BG_COLOR.htmlStyleKey()));
            richT.setTextColor(item.getTagStyle().get(TextTypeStyleEnum.TEXT_COLOR.htmlStyleKey()));
            richT.setRange(item.getCurrentIndex() + "," + item.getTextLength());
            item.getRichText().add(richT);
            richText.add(richT);
        }
        // font类型富文本转换
        if (StringUtils.isNotEmpty(item.getTagStyle().get(TextTypeStyleEnum.FONT_SIZE.htmlStyleKey())) || item.getTagStyle().get("bold") != null
                || "bold".equals(item.getTagStyle().get("font-weight"))) {
            TangramItemVo.RichText richT = new TangramItemVo.RichText();
            richT.setRichType(RichTextTypeEnum.FONT.getValue());
            Boolean bold = "true".equals(item.getTagStyle().get("bold")) || "bold".equals(item.getTagStyle().get("font-weight"));
            richT.setBold(bold.toString());
            richT.setFontSize(getFontSize(item.getTagStyle()));
            richT.setRange(item.getCurrentIndex() + "," + item.getTextLength());
            item.getRichText().add(richT);
            richText.add(richT);
        }
        // 清除富文本处理信息
        item.clearHandleInfo();
        return richText;
    }

    /**
     * 图片参数转换
     *
     * @param item
     */
    private static void transformImgItem(TangramTransformHandler item) {
        TangramItemVo.ImgParams imgParams = new TangramItemVo.ImgParams();
        imgParams.setImgUrl(item.getTagStyle().get(ImgTypeStyleEnum.IMG_URL.htmlStyleKey()));
        imgParams.setImgHeight(item.getTagStyle().get(ImgTypeStyleEnum.IMG_HEIGHT.htmlStyleKey()));
        imgParams.setImgWidth(item.getTagStyle().get(ImgTypeStyleEnum.IMG_WIDTH.htmlStyleKey()));
        // 商品smallIcon
        imgParams.setSmallIcon(item.getTagStyle().get("smallIcon".toLowerCase()));
        int imgIndex = item.getTagList().indexOf(RichTextHtmlTagEnum.IMG.tag());
        if (imgIndex >= 1) {
            for (int i = imgIndex - 1; i >= 0; i--) {
                if ("a".equals(item.getTagList().get(i))) {
                    TangramItemVo.ActionParams action = new TangramItemVo.ActionParams();
                    action.setActionType(ActionTypeEnum.JUMP.getValue());
                    action.setActionUrl(item.getTagStyle().get("href"));
                    item.setActionParams(action);
                    break;
                }
            }
        }
        item.setImgParams(imgParams);
    }

    private static void findSubRichTextChild(Node node, TangramTransformHandler item) {
        // 文本叶子节点
        if (node instanceof TextNode) {
            TextNode textNode = (TextNode) node;
            int textLength = textNode.text().length();
            item.setTextLength(textLength);
            transformRichTextItem(item);
            item.setCurrentIndex(item.getCurrentIndex() + textLength);
            // html元素
        } else if (node instanceof Element) {
            Element ele = ((Element) node);
            item.getTagList().add(ele.tagName());
            getStyleFromNode(ele, item);
            ele.childNodes().forEach(nd -> findSubRichTextChild(nd, item));
        }
    }

    /**
     * 递归子节点
     *
     * @param node
     * @param item
     */
    private static void findChildNode(Node node, TangramTransformHandler item) {
        // 文本叶子节点
        if (node instanceof TextNode) {
            // 获取父节点所有样式
            getStyleMapFromParent(node.parentNode(), item);
            TextNode textNode = (TextNode) node;
            int textLength = textNode.text().length();
            item.setTextLength(textLength);
            transformRichTextItem(item);
            item.setCurrentIndex(item.getCurrentIndex() + textLength);
            // html元素
        } else if (node instanceof Element) {
            Element ele = ((Element) node);
            item.getTagList().add(ele.tagName());
            ele.childNodes().forEach(nd -> findChildNode(nd, item));
        }
    }

    /**
     * 获取文本父级标签属性
     *
     * @param node
     * @param handler
     */
    private static void getStyleMapFromParent(Node node, TangramTransformHandler handler) {
        if (node != null) {
            Element ele = (Element) node;
            if (RichTextHtmlTagEnum.STRONG.tag().equals(ele.tagName())) {
                handler.getTagStyle().put("bold", Boolean.TRUE.toString());
            }
            Map<String, String> attrMap = getNodeAttr(ele);
            handler.getTagStyle().putAll(attrMap);
        }
        if (node == null || handler.getFirstNode() == node.parentNode()) {
            return;
        }
        getStyleMapFromParent(node.parentNode(), handler);
    }

    /**
     * 获取节点标签属性
     *
     * @param ele
     * @param item
     */
    private static void getStyleFromNode(Element ele, TangramTransformHandler item) {
        if (RichTextHtmlTagEnum.STRONG.tag().equals(ele.tagName())) {
            item.getTagStyle().put("bold", Boolean.TRUE.toString());
        }
        Map<String, String> attrMap = getNodeAttr(ele);
        item.getTagStyle().putAll(attrMap);
    }

    /**
     * 属性转为map
     *
     * @param ele
     * @return
     */
    private static Map<String, String> getNodeAttr(Element ele) {
        Map<String, String> map = new HashMap<>();
        for (Attribute attr : ele.attributes()) {
            map.put(attr.getKey(), attr.getValue());
        }
        if (StringUtils.isNotEmpty(map.get("style"))) {
            String[] values = map.get("style").split(";");
            for (String value : values) {
                String[] kv = value.split(":");
                if (kv.length != 2) {
                    continue;
                }
                String kv0 = StringUtils.trimToEmpty(kv[0]);
                if (TextTypeStyleEnum.FONT_SIZE.htmlStyleKey().equals(kv0) || TextTypeStyleEnum.LINE_SPACE.htmlStyleKey().equals(kv0)) {
                    map.put(kv0, StringUtils.trimToEmpty(kv[1].substring(0, kv[1].length() - 2)));
                } else {
                    map.put(kv0, StringUtils.trimToEmpty(kv[1]));
                }
            }
        }
        return map;
    }

}
