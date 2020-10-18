package com.tcl.uf.member.vo;

import java.util.List;

public class PageInfoVo<T> {

    private Integer pageNum;    //当前页

    private Integer pageSize;   //每页大小

    private Integer totalPage;  //总页数

    private Integer totalSize;  //总条数

    private List<T> data;       //数据列表

    public PageInfoVo(Integer pageNum, Integer pageSize, Integer totalSize, List<T> data) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalSize = totalSize;
        this.data = data;
        this.totalPage = (totalSize % pageSize) == 0 ? totalSize / pageSize : totalSize / pageSize + 1;
    }

    public PageInfoVo() {
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
