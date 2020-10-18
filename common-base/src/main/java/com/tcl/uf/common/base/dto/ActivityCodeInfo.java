package com.tcl.uf.common.base.dto;

public class ActivityCodeInfo {

    public ActivityCodeInfo() {
    }

    public ActivityCodeInfo(Integer a, String c) {
        this.a = a;
        this.c = c;
    }

    /**
     * 活动ID
     */
    private Integer a;

    /**
     * 公司ID
     */
    private String c;

    /**
     * 用户ID
     */
    private String u;

    /**
     * 传播通道
     */
    private String d;
    
    /**
     * 传播标记
     */
    private String s;
    
    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    public String getU() {
        return u;
    }

    public void setU(String u) {
        this.u = u;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}
}
