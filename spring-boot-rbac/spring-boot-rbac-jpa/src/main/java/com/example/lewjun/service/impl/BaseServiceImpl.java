package com.example.lewjun.service.impl;

import com.example.lewjun.service.IBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
public class BaseServiceImpl<T, ID> implements IBaseService<T, ID> {
    protected JpaRepository<T, ID> jpaRepository;

    BaseServiceImpl(final JpaRepository<T, ID> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public T save(final T t) {
        return jpaRepository.save(t);
    }

    @Override
    public List<T> queryAll() {
        return jpaRepository.findAll();
    }

    @Override
    public void delete(final T t) {
        jpaRepository.delete(t);
    }

    @Override
    public void deleteById(final ID id) {
        try {
            jpaRepository.deleteById(id);
        } catch (final EmptyResultDataAccessException e) {
//            throw new RuntimeException("信息不存在"); // 不处理，直接返回给前端，提示删除成功
            log.error("【出现异常：】", e);
        }
    }

    @Override
    public Optional<T> findById(ID id) {
        return jpaRepository.findById(id);
    }

    @Override
    public T update(T t) {
        return jpaRepository.save(t);
    }
}
