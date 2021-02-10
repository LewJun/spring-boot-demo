package com.example.lewjun.dao;

import com.example.lewjun.domain.BaseObj;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T extends BaseObj> {

    int save(String sql, Object... args);

    int delete(String sql, Object... args);

    int update(String sql, Object... args);

    <R> R queryForObject(String sql, Class<R> clazz, Object... args);

    T queryForObj(String sql, Class<T> clazz, Object... args);

    <R> List<R> queryForList(String sql, Class<R> clazz, Object... args);

    List<T> queryForObjs(String sql, Class<T> clazz, Object... args);

    default int deleteById(final String sql, final Serializable pk) {
        throw new RuntimeException("deleteById must be implements.");
    }
}
