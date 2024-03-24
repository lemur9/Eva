package com.lemur.eva.core.page;

import lombok.Data;

import java.util.List;

/**
 * 分页工具类
 *
 */
@Data
public class PageData<T> {

    /**
     * 总记录数
     */
    private int total;

    /**
     * 列表数据
     */
    private List<T> list;

    /**
     * 分页
     * @param list   列表数据
     * @param total  总记录数
     */
    public PageData(List<T> list, long total) {
        this.list = list;
        this.total = (int)total;
    }
}