package com.example.lewjun.dao.impl;

import com.example.lewjun.dao.Ab01Dao;
import com.example.lewjun.domain.Ab01;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class Ab01DaoImpl extends BaseDaoImpl<Ab01> implements Ab01Dao {
    @Override
    public int deleteById(final String sql, final Serializable pk) {
        return super.delete(sql, pk);
    }
}
