package com.example.lewjun.service;

import com.example.lewjun.domain.Ac01;

import java.util.List;

public interface Ac01Service {
    List<Ac01> getList();

    void save(Ac01 ac01);

    Ac01 get(int aac001);

    void delete(int aac001);

    void update(Ac01 ac01);
}
