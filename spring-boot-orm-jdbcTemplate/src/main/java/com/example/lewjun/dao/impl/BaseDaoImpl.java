package com.example.lewjun.dao.impl;

import com.example.lewjun.dao.BaseDao;
import com.example.lewjun.domain.BaseObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public abstract class BaseDaoImpl<T extends BaseObj> implements BaseDao<T> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(final String sql, final Object... args) {
        return update(sql, args);
    }

    @Override
    public int delete(final String sql, final Object... args) {
        return update(sql, args);
    }

    @Override
    public int update(final String sql, final Object... args) {
        return jdbcTemplate.update(sql, args);
    }

    @Override
    public <R> R queryForObject(final String sql, final Class<R> clazz, final Object... args) {
        return jdbcTemplate.queryForObject(sql, args, clazz);
    }

    @Override
    public T queryForObj(final String sql, final Class<T> clazz, final Object... args) {
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(clazz), args);
    }

    @Override
    public <R> List<R> queryForList(final String sql, final Class<R> clazz, final Object... args) {
        return jdbcTemplate.queryForList(sql, clazz, args);
    }

    @Override
    public List<T> queryForObjs(final String sql, final Class<T> clazz, final Object... args) {
        return jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<>(clazz));
    }
}
