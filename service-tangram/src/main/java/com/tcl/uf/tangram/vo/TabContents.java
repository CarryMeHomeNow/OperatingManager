package com.tcl.uf.tangram.vo;

/**
 * @author zhongfk on 2020/8/20.
 * @version 1.0
 * @Desc 智能分类
 */
public class TabContents {
    private String titleContent;
    private String subtitleContent;
    private Double titleFontSize = 15.0;
    private Double subTitleFontSize = 10.0;
    private String selectedColor = "#CC0000";
    private String defaultColor = "#999999";
    private Long selectionId;

    private ActionParams actionParams;

    public Long getSelectionId() {
        return selectionId;
    }

    public void setSelectionId(Long selectionId) {
        this.selectionId = selectionId;
    }

    public String getTitleContent() {
        return titleContent;
    }

    public void setTitleContent(String titleContent) {
        this.titleContent = titleContent;
    }

    public String getSubtitleContent() {
        return subtitleContent;
    }

    public void setSubtitleContent(String subtitleContent) {
        this.subtitleContent = subtitleContent;
    }

    public Double getTitleFontSize() {
        return titleFontSize;
    }

    public void setTitleFontSize(Double titleFontSize) {
        this.titleFontSize = titleFontSize;
    }

    public Double getSubTitleFontSize() {
        return subTitleFontSize;
    }

    public void setSubTitleFontSize(Double subTitleFontSize) {
        this.subTitleFontSize = subTitleFontSize;
    }

    public String getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(String selectedColor) {
        this.selectedColor = selectedColor;
    }

    public String getDefaultColor() {
        return defaultColor;
    }

    public void setDefaultColor(String defaultColor) {
        this.defaultColor = defaultColor;
    }

    public ActionParams getActionParams() {
        return actionParams;
    }

    public void setActionParams(ActionParams actionParams) {
        this.actionParams = actionParams;
    }

    @Override
    public String toString() {
        return "{" +
                "titleContent:'" + titleContent + '\'' +
                ", subtitleContent:'" + subtitleContent + '\'' +
                ", titleFontSize:" + titleFontSize +
                ", subTitleFontSize:" + subTitleFontSize +
                ", selectedColor:'" + selectedColor + '\'' +
                ", defaultColor:'" + defaultColor + '\'' +
                ", selectionId:" + selectionId +
                ", actionParams:" + actionParams +
                '}';
    }
}
