package com.tcl.uf.tangram.vo;

/**
 * @author zhongfk on 2020/8/24.
 * @version 1.0
 */
public class VipTextParams {

    //会员名称
    private String textContent;
    //会员等级
    private String vipLevel;

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(String vipLevel) {
        this.vipLevel = vipLevel;
    }

    @Override
    public String toString() {
        return "VipTextParams{" +
                "textContent='" + textContent + '\'' +
                ", vipLevel='" + vipLevel + '\'' +
                '}';
    }
}
