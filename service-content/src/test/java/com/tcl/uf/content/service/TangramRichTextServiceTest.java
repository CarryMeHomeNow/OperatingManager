package com.tcl.uf.content.service;

import com.alibaba.fastjson.JSON;
import com.tcl.uf.common.base.util.tangram.TangramRichTextUtil;
import com.tcl.uf.common.base.util.tangram.enums.ItemTypeEnum;
import com.tcl.uf.common.base.util.tangram.vo.TangramCartVo;
import com.tcl.uf.common.base.util.tangram.vo.TangramItemVo;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
public class TangramRichTextServiceTest {

    @Test
    public void testToTangramJson() {
        String html = "<p>我<span style=\"color: #2dc26b;\">是第一段</span></p>↵<p style=\"text-align: center;\">我是第<span style=\"background-color: #f1c40f;\">二段</span></p>↵<p style=\"text-align: right;\"><span style=\"color: #f1c40f;\"><span style=\"color: #2dc26b;\">我是</span>第三段</span></p>↵<p style=\"text-align: right;\"><span style=\"color: #f1c40f;\">我<span style=\"color: #2dc26b;\">是第</span>四段</span></p>↵<p style=\"text-align: right;\"><span style=\"color: #f1c40f;\">我<strong>是<span style=\"color: #2dc26b;\">第五</span></strong>段</span></p>↵<p style=\"text-align: right;\"><span style=\"color: #f1c40f;\">我是第<span style=\"color: #2dc26b;\">六段</span></span></p>↵<p><img src=\"http://i0.um.tcl.com/FhgyDRqmGYvHAXr4CRcpFkXe2B6L\" alt=\"\" /></p>↵<p style=\"text-align: right;\">我是第七段</p>↵<p style=\"text-align: right;\">我是第八段</p>↵<p style=\"text-align: center;\">我是第九段</p>↵<p style=\"text-align: center;\">我是第十段</p>↵<p><img src=\"http://i0.um.tcl.com/FiyJjdY4IsbS0SS-gXmsSO1jPMNC\" alt=\"\" /></p>↵<p>我是第十一段</p>↵<p>我是第十二段文本<span style=\"color: #2dc26b; background-color: #e03e2d;\"><span style=\"font-family: 'Helvetica Neue', Helvetica, 'PingFang SC', 'Hiragino Sans GB', ' Microsoft YaHei', 微软雅黑, Arial, sans-serif;\">我是第十二段文本</span><span style=\"font-family: 'Helvetica Neue', Helvetica, 'PingFang SC', 'Hiragino Sans GB', ' Microsoft YaHei', 微软雅黑, Arial, sans-serif;\">我是第十二段文本</span><span style=\"font-family: 'Helvetica Neue', Helvetica, 'PingFang SC', 'Hiragino Sans GB', ' Microsoft YaHei', 微软雅黑, Arial, sans-serif;\">我是第十二段文本</span><span style=\"font-family: 'Helvetica Neue', Helvetica, 'PingFang SC', 'Hiragino Sans GB', ' Microsoft YaHei', 微软雅黑, Arial, sans-serif;\">我是第十二段文本</span><span style=\"font-family: 'Helvetica Neue', Helvetica, 'PingFang SC', 'Hiragino Sans GB', ' Microsoft YaHei', 微软雅黑, Arial, sans-serif;\">我是第十二段文本</span></span><span style=\"font-family: 'Helvetica Neue', Helvetica, 'PingFang SC', 'Hiragino Sans GB', ' Microsoft YaHei', 微软雅黑, Arial, sans-serif;\"><span style=\"color: #2dc26b; background-color: #e03e2d;\">我是第十二</span>段文本</span><span style=\"font-family: 'Helvetica Neue', Helvetica, 'PingFang SC', 'Hiragino Sans GB', ' Microsoft YaHei', 微软雅黑, Arial, sans-serif;\">我是第十二段文本</span><span style=\"font-family: 'Helvetica Neue', Helvetica, 'PingFang SC', 'Hiragino Sans GB', ' Microsoft YaHei', 微软雅黑, Arial, sans-serif;\">我是第十二段文本</span><span style=\"font-family: 'Helvetica Neue', Helvetica, 'PingFang SC', 'Hiragino Sans GB', ' Microsoft YaHei', 微软雅黑, Arial, sans-serif;\">我是第十二段文本</span><span style=\"font-family: 'Helvetica Neue', Helvetica, 'PingFang SC', 'Hiragino Sans GB', ' Microsoft YaHei', 微软雅黑, Arial, sans-serif;\">我是第十二段文本</span><span style=\"font-family: 'Helvetica Neue', Helvetica, 'PingFang SC', 'Hiragino Sans GB', ' Microsoft YaHei', 微软雅黑, Arial, sans-serif;\">我是第十二段文本</span><span style=\"font-family: 'Helvetica Neue', Helvetica, 'PingFang SC', 'Hiragino Sans GB', ' Microsoft YaHei', 微软雅黑, Arial, sans-serif;\">我是第十二段文本</span></p>\n"
                + "<p style=\"text-align: center;\"><strong>中新经<span style=\"color: #e03e2d;font-size: 24px;\">纬版权所有，未</span><span style=\"background-color: #e67e23;\"><span style=\"color: #e03e2d;\">经</span>书面授权，任何单位<span style=\"color: #2dc26b;\">及个人</span></span></strong><span style=\"background-color: #e67e23;\"><span style=\"color: #2dc26b;\">不</span></span><strong><span style=\"background-color: #e67e23;\"><span style=\"color: #2dc26b;\">得转</span></span><span style=\"color: #2dc26b;\">载、摘</span>编以其它方式使用。</strong></p>"
                + "<div class=\"goods-item editor-no-edit\" data-custom-type=\"commodityList\" data-commodity-type=\"commodityList\" data-custom-category=\"1\">\n"
                + "\t<div data-custom-type=\"goodsImgWrap\">\n"
                + "\t\t<a class=\"goods-pic-box\" href=\"tclplus://mall/goodsDetail?sku=123\" data-custom-type=\"actionParams\"> \n"
                + "\t\t<img src=\"http://10.120.12.22:8899/upload/pic/A159782349535787378278.jpeg\" alt=\"\" width=\"4032\" height=\"3024\" data-custom-type=\"imgParams\" /> \n"
                + "\t\t</a>\n"
                + "\t</div>\n"
                + "<div class=\"goods-info\" data-custom-type=\"goodsInfoWrap\">\n"
                + "\t<div class=\"goods-title\" data-custom-type=\"titleParams\">电视Q9</div>\n"
                + "\t<div class=\"goods-subtitle\" data-custom-type=\"subtitleParams\">量子</div>\n"
                + "\t<div class=\"goods-current-price\" data-custom-type=\"currentPriceParams\">8848</div>\n"
                + "\t<div class=\"goods-origin-price\" data-custom-type=\"originalPriceParams\" data-custom-style=\"font-size:15;color:#ced4d9;\">9000</div>\n"
                + "</div>\n"
                + "</div>"

                + "<div class=\"coupon-item editor-no-edit\" data-custom-type=\"coupon\" data-coupon-id=\"123\" red-point-status=\"true\" data-custom-category=\"0\">"
                + "  <div class=\"coupon-left\" data-custom-type=\"couponLeftInfo\">\n"
                + "    <div class=\"coupon-status\" data-custom-type=\"statusParams\" data-custom-style=\"font-size:12;color:#ced4d9;\">待领取</div>\n"
                + "  </div>\n"
                + "  <div class=\"coupon-right\" data-custom-type=\"couponRightInfo\" >\n"
                + "    <div class=\"coupon-title\" data-custom-type=\"statusTipParams\" data-custom-style=\"font-size:13;color:#ced422;\">优惠券22</div>\n"
                + "    <div class=\"coupon-endtime\" data-custom-type=\"expireTimeParams\" data-custom-style=\"font-size:12;color:#ffffd9;\">有效期至2023</div>\n"
                + "  </div>\n"
                + "</div>";
        String json = TangramRichTextUtil.toTangramJson(html);
        Assert.assertNotNull(json);
        TangramCartVo vo = JSON.parseObject(json, TangramCartVo.class);
        TangramCartVo tangramCartVo = new TangramCartVo();
        List<TangramItemVo> items = new ArrayList<>();
        // 当前用于比较的text item  将相同的item合并
        MergeItem mergeItem = new MergeItem();
        int size = vo.getItems().size();
        for (int i = 0; i < size; i++) {
            TangramItemVo item = vo.getItems().get(i);
            if (!ItemTypeEnum.TEXT.getValue().equals(item.getType())) {
                if (mergeItem.getItem() != null) {
                    items.add(mergeItem.getItem());
                    mergeItem.setCurrentIndex(0);
                    mergeItem.setKey(null);
                    mergeItem.setItem(null);
                }
                items.add(item);
                continue;
            }
            if (i == size - 1) {
                mergeLastItem(items, item, mergeItem);
                break;
            }
            if (StringUtils.isEmpty(mergeItem.getKey())) {
                changeMergeItem(item, mergeItem);
                continue;
            }
            compareItem(items, item, mergeItem);
        }
        tangramCartVo.setItems(items);
        System.out.println(JSON.toJSONString(tangramCartVo));
    }

    private static void mergeLastItem(List<TangramItemVo> items, TangramItemVo item, MergeItem mergeItem) {
        if (mergeItem.getItem() == null) {
            items.add(item);
        } else if (getKey(item).equals(mergeItem.getKey())) {
            TangramItemVo tangramItemVo = mergeItem(item, mergeItem);
            items.add(tangramItemVo);
        } else {
            items.add(mergeItem.getItem());
            items.add(item);
        }
    }

    private static void changeMergeItem(TangramItemVo item, MergeItem mergeItem) {
        mergeItem.setItem(item);
        mergeItem.setKey(getKey(item));
        mergeItem.setCurrentIndex(item.getTextContent().length());
    }

    private static String getKey(TangramItemVo item) {
        return item.getType() + "_" + item.getTextAlignment() + "_" + item.getTextColor() + "_" + item.getFontSize() + "_" + item.getLineSpace();
    }

    private static void compareItem(List<TangramItemVo> items, TangramItemVo item, MergeItem mergeItem) {
        if (getKey(item).equals(mergeItem.getKey())) {
            mergeItem.setItem(mergeItem(item, mergeItem));
            mergeItem.setCurrentIndex(mergeItem.getCurrentIndex() + 1 + item.getTextContent().length());
        } else {
            items.add(mergeItem.getItem());
            changeMergeItem(item, mergeItem);
        }
    }

    private static TangramItemVo mergeItem(TangramItemVo item, MergeItem mergeItem) {
        TangramItemVo textItem = new TangramItemVo();
        textItem.setTextContent(mergeItem.getItem().getTextContent() + "\n" + item.getTextContent());
        textItem.setTextAlignment(item.getTextAlignment());
        textItem.setTextColor(item.getTextColor());
        textItem.setFontSize(item.getFontSize());
        textItem.setLineSpace(item.getLineSpace());
        textItem.setRichText(mergeItem.getItem().getRichText() == null ? new ArrayList<>() : mergeItem.getItem().getRichText());
        if (item.getRichText() != null && !item.getRichText().isEmpty()) {
            List<TangramItemVo.RichText> richTexts = item.getRichText();
            for (TangramItemVo.RichText richText : richTexts) {
                String[] index = richText.getRange().split(",");
                int start = Integer.parseInt(index[0]) + mergeItem.getCurrentIndex() + 1;
                richText.setRange(start + "," + index[1]);
            }
            textItem.getRichText().addAll(item.getRichText());
        }
        return textItem;
    }

    private static class MergeItem {
        private TangramItemVo item;
        private String key;
        private Integer currentIndex;

        public TangramItemVo getItem() {
            return item;
        }

        public void setItem(TangramItemVo item) {
            this.item = item;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Integer getCurrentIndex() {
            return currentIndex;
        }

        public void setCurrentIndex(Integer currentIndex) {
            this.currentIndex = currentIndex;
        }
    }
}
