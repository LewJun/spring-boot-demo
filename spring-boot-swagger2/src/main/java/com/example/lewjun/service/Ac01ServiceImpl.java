package com.example.lewjun.service;

import com.example.lewjun.domain.Ac01;
import com.example.lewjun.repository.Ac01Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Ac01ServiceImpl implements Ac01Service {
    @Autowired
    private Ac01Repository ac01Repository;

    @Override
    public List<Ac01> getList() {
        return ac01Repository.getList();
    }

    @Override
    public void save(final Ac01 ac01) {
        ac01Repository.save(ac01);
    }

    @Override
    public Ac01 get(final int aac001) {
        return ac01Repository.get(aac001);
    }

    @Override
    public void delete(final int aac001) {
        ac01Repository.delete(aac001);
    }

    @Override
    public void update(final Ac01 ac01) {
        ac01Repository.update(ac01);
    }
}
