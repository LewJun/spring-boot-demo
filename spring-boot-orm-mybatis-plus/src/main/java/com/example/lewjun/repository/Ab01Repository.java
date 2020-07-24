package com.example.lewjun.repository;

import com.example.lewjun.domain.Ab01;

import java.util.List;

public interface Ab01Repository {
    List<Ab01> getList();

    void save(Ab01 ab01);

    Ab01 get(int aab001);

    void delete(int aab001);

    void update(Ab01 ab01);
}
