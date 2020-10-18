package com.tcl.uf.tangram.dto;

import java.io.Serializable;

/**
 * @Desc : 服务端respnose数据结构
 * @Author yxlong
 * @Date 2020/8/21 2:00 下午
 */
public class ServerResponse<T> implements Serializable {

    private String rspCode;
    private String rspMsg;
    private String transId;

    private T data;

    public ServerResponse(){}

    public ServerResponse(String rspCode, String rspMsg, String transId, T data) {
        this.rspCode = rspCode;
        this.rspMsg = rspMsg;
        this.transId = transId;
        this.data = data;
    }

    public ServerResponse(String rspCode, String rspMsg) {
        this.rspCode = rspCode;
        this.rspMsg = rspMsg;
    }

    /**
     * 返回成功,用参数中的数据填充data字段。
     *
     * @return
     */
    public static ServerResponse ok(Object data) {
        return new ServerResponse("200","成功",null,data);
    }

    public static ServerResponse failed(String rspCode,String rspMsg) {
        return new ServerResponse(rspCode,rspMsg);
    }

    public String getRspCode() {
        return rspCode;
    }

    public void setRspCode(String rspCode) {
        this.rspCode = rspCode;
    }

    public String getRspMsg() {
        return rspMsg;
    }

    public void setRspMsg(String rspMsg) {
        this.rspMsg = rspMsg;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
