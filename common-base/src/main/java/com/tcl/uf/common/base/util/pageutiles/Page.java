package com.tcl.uf.common.base.util.pageutiles;

/**
 * @author youyun.xu
 * @ClassName: Page
 * @Description:
 * @date 2020/7/27 上午17:14
 */
public class Page {

    /**
     * 总条目数
     */
    private Integer total;

    /**
     * 总页数
     */
    private Integer pages;

    /**
     * 当前页码
     */
    private Integer current;

    /**
     * 每页长度
     */
    private Integer size;

    public Page() {
        total = 0;
        pages = 0;
        size = 0;
        current = 0;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
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

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
