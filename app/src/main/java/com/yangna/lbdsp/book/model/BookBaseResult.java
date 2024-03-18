package com.yangna.lbdsp.book.model;


import com.yangna.lbdsp.book.bean.HnBook;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description: 基础实体类
 */
public class BookBaseResult {
    private Integer total;
    private Integer size;
    private Integer current;

    private List orders;

    private Boolean optimizeCountSql;

    private Boolean searchCount;

    private Boolean maxLimit;

    private Boolean countId;

    private Integer pages;

    private List<Map<String,Object>> records=new ArrayList<Map<String,Object>>();


    public BookBaseResult() {
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

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public List getOrders() {
        return orders;
    }

    public void setOrders(List orders) {
        this.orders = orders;
    }

    public Boolean getOptimizeCountSql() {
        return optimizeCountSql;
    }

    public void setOptimizeCountSql(Boolean optimizeCountSql) {
        this.optimizeCountSql = optimizeCountSql;
    }

    public Boolean getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(Boolean searchCount) {
        this.searchCount = searchCount;
    }

    public Boolean getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(Boolean maxLimit) {
        this.maxLimit = maxLimit;
    }

    public Boolean getCountId() {
        return countId;
    }

    public void setCountId(Boolean countId) {
        this.countId = countId;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public List<Map<String,Object>> getRecords() {
        return records;
    }

    public void setRecords(List<Map<String,Object>> records) {
        this.records = records;
    }
}

