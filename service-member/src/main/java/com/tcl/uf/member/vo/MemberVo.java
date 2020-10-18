package com.tcl.uf.member.vo;

/**
 * @author gaofan
 */
public class MemberVo {
    /**
     * 会员等级
     */
    private String level;
    /**
     * 会员成长值
     */
    private String growValue;
    /**
     * 距离下一等级成长值
     */
    private String nextValueDifference;
    /**
     * 下一等级
     */
    private String nextLevel;
    /**
     *  等级名称
     */
    private String levelName;

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getGrowValue() {
        return growValue;
    }

    public void setGrowValue(String growValue) {
        this.growValue = growValue;
    }

    public String getNextValueDifference() {
        return nextValueDifference;
    }

    public void setNextValueDifference(String nextValueDifference) {
        this.nextValueDifference = nextValueDifference;
    }

    public String getNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(String nextLevel) {
        this.nextLevel = nextLevel;
    }
}
