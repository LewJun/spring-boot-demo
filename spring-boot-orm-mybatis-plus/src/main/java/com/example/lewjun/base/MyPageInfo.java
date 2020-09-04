package com.example.lewjun.base;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class MyPageInfo<T> extends Page<T> {
    protected List<T> records;
    protected long total;
    protected long size;
    protected long current;
    @JsonIgnore
    protected long pages;
    @JsonIgnore
    protected List<OrderItem> orders;
    @JsonIgnore
    protected boolean optimizeCountSql;
    @JsonIgnore
    protected boolean searchCount;
    @JsonIgnore
    protected boolean hitCount;
/*

    @Override
    public List<T> getRecords() {
        return records;
    }

    @Override
    public MyPageInfo<T> setRecords(final List<T> records) {
        this.records = records;
        return this;
    }

    @Override
    public long getTotal() {
        return total;
    }

    @Override
    public MyPageInfo<T> setTotal(final long total) {
        this.total = total;
        return this;
    }

    @Override
    public long getSize() {
        return size;
    }

    @Override
    public MyPageInfo<T> setSize(final long size) {
        this.size = size;
        return this;
    }

    @Override
    public long getCurrent() {
        return current;
    }

    @Override
    public MyPageInfo<T> setCurrent(final long current) {
        this.current = current;
        return this;
    }

    @Override
    public List<OrderItem> getOrders() {
        return orders;
    }

    @Override
    public void setOrders(final List<OrderItem> orders) {
        this.orders = orders;
    }

    @Override
    public boolean isOptimizeCountSql() {
        return optimizeCountSql;
    }

    @Override
    public MyPageInfo<T> setOptimizeCountSql(final boolean optimizeCountSql) {
        this.optimizeCountSql = optimizeCountSql;
        return this;
    }

    @Override
    public boolean isSearchCount() {
        return searchCount;
    }

    @Override
    public MyPageInfo<T> setSearchCount(final boolean searchCount) {
        this.searchCount = searchCount;
        return this;
    }

    @Override
    public boolean isHitCount() {
        return hitCount;
    }

    @Override
    public void setHitCount(final boolean hitCount) {
        this.hitCount = hitCount;
    }*/
}
