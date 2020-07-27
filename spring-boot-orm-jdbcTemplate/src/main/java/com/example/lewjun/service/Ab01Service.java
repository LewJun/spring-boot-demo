package com.example.lewjun.service;

import com.example.lewjun.domain.Ab01;

import java.util.List;

public interface Ab01Service {
    int save(Ab01 ab01);

    int delete(Integer pk);

    int update(Ab01 ab01);

    Ab01 queryById(Integer id);

    List<Ab01> queryByAab002(String aab002);

    List<Ab01> queryByAab003(String aab003);

    List<Ab01> queryAll();

    int getAb01Count();

    List<Integer> queryAllAab001();
}
