package com.example.lewjun.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface MyBaseMapper<T> extends BaseMapper<T> {
    default int updateBySelective(final T entity) {
        throw new RuntimeException("未实现");
    }
}
