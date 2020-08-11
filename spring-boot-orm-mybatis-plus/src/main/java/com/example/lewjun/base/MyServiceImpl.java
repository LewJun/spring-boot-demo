package com.example.lewjun.base;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class MyServiceImpl<M extends MyBaseMapper<T>, T> extends ServiceImpl<M, T> implements MyIService<T> {
    @Autowired
    protected M myBaseMapper;

    public MyServiceImpl() {
        super.baseMapper = myBaseMapper;
    }
}