package com.tcl.uf.tangram.vo;

import java.util.List;

/**
 * @author zhongfk on 2020/9/1.
 * @version 1.0
 * @Desc  积分明细
 */
public class PointDetailVO {
    private String type;
    private String titleContent;
    private SumTextParams sumTextParams;
    private SumPointparams sumPointparams;
    private List<Items> items;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitleContent() {
        return titleContent;
    }

    public void setTitleContent(String titleContent) {
        this.titleContent = titleContent;
    }

    public SumTextParams getSumTextParams() {
        return sumTextParams;
    }

    public void setSumTextParams(SumTextParams sumTextParams) {
        this.sumTextParams = sumTextParams;
    }

    public SumPointparams getSumPointparams() {
        return sumPointparams;
    }

    public void setSumPointparams(SumPointparams sumPointparams) {
        this.sumPointparams = sumPointparams;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }
}
