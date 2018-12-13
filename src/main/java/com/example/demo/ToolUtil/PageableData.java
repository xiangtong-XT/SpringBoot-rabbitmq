package com.example.demo.ToolUtil;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 多彩校园服务器后台分页数据响应体
 * @author: zqy
 * @date: 2018/4/16 14:29
 * @since: 1.0-SNAPSHOT
 * @note: none
 */

@Getter
@Setter
@NoArgsConstructor
public class PageableData {
    int pageNum;

    int pageSize;

    int pages;

    long total;

    List list;

    Collection attached;

    public PageableData(int pageSize, Collection attached) {
        this.pageNum = 1;
        this.pageSize = pageSize;
        this.pages = 0;
        this.total = 0;
        this.list = Collections.emptyList();
        this.attached = attached;
    }

    public PageableData(Page page, List items, Collection attached) {
        final int pageIndex = page.getNumber();
        this.pageNum = 0 >= pageIndex ? 1 : pageIndex + 1;
        this.pageSize = page.getSize();
        this.pages = page.getTotalPages();
        this.total = page.getTotalElements();
        this.list = items;
        this.attached = attached;
    }
}
