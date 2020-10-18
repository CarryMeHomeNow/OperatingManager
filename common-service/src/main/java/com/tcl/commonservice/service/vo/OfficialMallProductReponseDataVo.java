package com.tcl.commonservice.service.vo;

import java.util.List;

public class OfficialMallProductReponseDataVo {

    private Integer currentPage;
    private Integer numPerPage;
    private Integer totalCount;
    private List<ProductVo> recordList;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(Integer numPerPage) {
        this.numPerPage = numPerPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<ProductVo> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<ProductVo> recordList) {
        this.recordList = recordList;
    }
}
