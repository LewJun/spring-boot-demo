package com.example.lewjun.base;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class MyServiceImpl<M extends MyBaseMapper<T>, T> extends ServiceImpl<M, T> implements MyIService<T> {
    public MyServiceImpl() {
    }

    // spring 推荐在构造方法上注入属性
    @Autowired
    public MyServiceImpl(final M myBaseMapper) {
        this();
        super.baseMapper = myBaseMapper;
    }
}