package com.example.lewjun.base;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.Optional;

public interface MyIService<T> extends IService<T> {

    default Optional<T> getByIdOptional(final Serializable id) {
        return Optional.ofNullable(getBaseMapper().selectById(id));
    }


    default Optional<T> getOneOptional(final Wrapper<T> queryWrapper) {
        return Optional.ofNullable(getBaseMapper().selectOne(queryWrapper));
    }
}
