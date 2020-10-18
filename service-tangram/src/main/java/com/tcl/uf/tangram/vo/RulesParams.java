package com.tcl.uf.tangram.vo;

/**
 * @author zhongfk on 2020/8/26.
 * @version 1.0
 */
public class RulesParams {
    private String actionType = "jump";
    private String actionUrl;

    private ActionParams actionParams;

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

    public ActionParams getActionParams() {
        return actionParams;
    }

    public void setActionParams(ActionParams actionParams) {
        this.actionParams = actionParams;
    }
}
