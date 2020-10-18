package com.tcl.uf.common.base.util.tangram;

import com.tcl.uf.common.base.util.tangram.vo.TangramItemVo;
import org.jsoup.nodes.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TangramTransformHandler extends TangramItemVo {

    // 富文本处理
    private Integer currentIndex;// 该段富文本在整个文本中的起始index
    private List<String> tagList;// 该段富文本的标签列表
    private Map<String, String> tagStyle;// 用于放置该段富文本的样式集合
    private Integer textLength;// 该段富文本的长度
    private Node firstNode;// 遍历的一级节点

    public TangramTransformHandler() {
        this.currentIndex = 0;
        this.setRichText(new ArrayList<>());
        this.tagList = new ArrayList<>();
        this.tagStyle = new HashMap<>();
        this.textLength = 0;
    }

    public void clearHandleInfo() {
        this.tagList.clear();
        this.tagStyle.clear();
        this.textLength = 0;
    }

    public void clearTextIndex() {
        this.currentIndex = 0;
    }

    public boolean hasRichText() {
        return this.getRichText() != null && !this.getRichText().isEmpty();
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    public Map<String, String> getTagStyle() {
        return tagStyle;
    }

    public void setTagStyle(Map<String, String> tagStyle) {
        this.tagStyle = tagStyle;
    }

    public Integer getTextLength() {
        return textLength;
    }

    public void setTextLength(Integer textLength) {
        this.textLength = textLength;
    }

    public Integer getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(Integer currentIndex) {
        this.currentIndex = currentIndex;
    }

    public Node getFirstNode() {
        return firstNode;
    }

    public void setFirstNode(Node firstNode) {
        this.firstNode = firstNode;
    }
}
