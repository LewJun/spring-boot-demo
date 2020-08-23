package com.example.lewjun.service.impl;

import com.example.lewjun.service.IBaseService;
import org.springframework.data.jpa.repository.JpaRepository;

public class BaseServiceImpl<T, ID> implements IBaseService<T, ID> {
    protected JpaRepository<T, ID> jpaRepository;

    BaseServiceImpl(final JpaRepository<T, ID> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public T save(final T t) {
        return jpaRepository.save(t);
    }
}
