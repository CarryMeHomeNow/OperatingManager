package com.tcl.commonservice.service.vo;

import java.util.List;

public class OfficialMallCouponReponseDataVo {

    private Integer offset;
    private Integer limit;
    private Integer total;
    private Integer size;
    private Integer pages;
    private Integer current;
    private boolean searchCount;
    private List<CouponVo> records;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public boolean getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(boolean searchCount) {
        this.searchCount = searchCount;
    }

    public List<CouponVo> getRecords() {
        return records;
    }

    public void setRecords(List<CouponVo> records) {
        this.records = records;
    }
}
