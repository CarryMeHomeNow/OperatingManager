package com.tcl.uf.common.base.util.pageutiles;

import java.util.ArrayList;
import java.util.List;

/**
 * @author youyun.xu
 * @ClassName: ListWithPage
 * @Description:
 * @date 2020/7/27 上午17:14
 */
public class ListWithPage<T> {

    private List<T> list;
    private Page page;

    public ListWithPage(List<T> list, Page page) {
        this.list = list;
        this.page = page;
    }

    public ListWithPage() {
        list = new ArrayList<>();
        page = new Page();
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
