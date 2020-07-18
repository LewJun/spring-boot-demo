package com.example.lewjun.service;

import com.example.lewjun.domain.Ab01;
import com.example.lewjun.repository.Ab01Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Ab01ServiceImpl implements Ab01Service {
    @Autowired
    private Ab01Repository ab01Repository;

    @Override
    public List<Ab01> getList() {
        return ab01Repository.getList();
    }

    @Override
    public void save(final Ab01 ab01) {
        ab01Repository.save(ab01);
    }

    @Override
    public Ab01 get(final int aab001) {
        return ab01Repository.get(aab001);
    }

    @Override
    public void delete(final int aab001) {
        ab01Repository.delete(aab001);
    }

    @Override
    public void update(final Ab01 ab01) {
        ab01Repository.update(ab01);
    }
}
