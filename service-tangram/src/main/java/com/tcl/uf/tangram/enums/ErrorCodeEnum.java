package com.tcl.uf.tangram.enums;


/**
 * @Desc : 定义接口返回码及msg
 * @Author yxlong
 * @Date 2020/9/2 11:15 上午
 */
public enum  ErrorCodeEnum {

    /**
     * 系统错误码
     */
    SUCCESS("200","请求成功");

    private String code;
    private String msg;

    ErrorCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
