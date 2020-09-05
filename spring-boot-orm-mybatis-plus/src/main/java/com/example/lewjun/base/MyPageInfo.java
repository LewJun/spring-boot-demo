package com.example.lewjun.base;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class MyPageInfo<T> extends Page<T> {
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

    public MyPageInfo(final long current, final long size) {
        super(current, size);
    }

    @Override
    public String toString() {
        return "MyPageInfo{" +
                "records=" + records +
                ", total=" + total +
                ", size=" + size +
                ", current=" + current +
                ", pages=" + pages +
                ", orders=" + orders +
                ", optimizeCountSql=" + optimizeCountSql +
                ", searchCount=" + searchCount +
                ", hitCount=" + hitCount +
                '}';
    }
}
