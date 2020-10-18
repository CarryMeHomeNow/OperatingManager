package com.tcl.uf.common.base.util.pageutiles;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author youyun.xu
 * @ClassName: PageUtils
 * @Description:
 * @date 2020/7/27 上午17:14
 */
public class PageUtils {

    private static final int maxSize = 2000;
    private static final int initPageDefault = 0;
    private static final int initSizeDefault = 10;

    public static Pageable getPageFromRequestParam(HttpServletRequest request) {
        int pageNum = initPageDefault;
        int sizeNum = initSizeDefault;

        String page = request.getParameter("page");
        if (!StringUtils.isEmpty(page)) {
            pageNum = Integer.valueOf(page) - 1;
            if (pageNum < 0) {
                pageNum = 0;
            }
        }

        String size = request.getParameter("size");
        if (!StringUtils.isEmpty(size)) {
            sizeNum = Integer.valueOf(size);
        }

//        return new PageRequest(pageNum, sizeNum);
        // upgrade (hepeng)
        return PageRequest.of(pageNum, sizeNum);
    }

    public static Pageable getPageable(int page, int size) {
        if (size <= 0) {
            size = 5;
        }

        if (size > maxSize) {
            size = maxSize;
        }

        if (page < 0) {
            page = 0;
        }

//        return new PageRequest(page, size);
        // upgrade (hepeng)
        return PageRequest.of(page, size);
    }

    public static <T> ListWithPage<T> formatData(Page<T> page) {
        List<T> content = page.getContent();
        com.tcl.uf.common.base.util.pageutiles.Page newPage = new com.tcl.uf.common.base.util.pageutiles.Page();
        newPage.setCurrent(page.getNumber() + 1);
        newPage.setSize(page.getSize());
        newPage.setPages(page.getTotalPages());
        newPage.setTotal(Long.valueOf(page.getTotalElements()).intValue());
        return new ListWithPage<>(content, newPage);
    }

    public static <T> ListWithPage<T> formatData(List<T> list,Pageable pageable,Long total) {
        com.tcl.uf.common.base.util.pageutiles.Page newPage = new com.tcl.uf.common.base.util.pageutiles.Page();
        newPage.setCurrent(pageable.getPageNumber());
        newPage.setSize(pageable.getPageSize());
        if (total != null) {
            newPage.setPages((total.intValue() - 1) / pageable.getPageSize() + 1);
            newPage.setTotal(total.intValue());
        }
        return new ListWithPage<>(list, newPage);
    }
}
