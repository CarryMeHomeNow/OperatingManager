package com.tcl.commonservice.service.vo;

import java.util.List;

public class OfficialMallProductCategoryReponseDataVo {
    private String transId;
    private String code;
    private String message;
    List<ProductCategoryVo> data;

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ProductCategoryVo> getData() {
        return data;
    }

    public void setData(List<ProductCategoryVo> data) {
        this.data = data;
    }
}
