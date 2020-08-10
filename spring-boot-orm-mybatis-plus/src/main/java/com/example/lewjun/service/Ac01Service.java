package com.example.lewjun.service;

import com.example.lewjun.base.MyIService;
import com.example.lewjun.domain.Ac01;
import com.example.lewjun.domain.Ac01Ab01;

import java.util.List;

public interface Ac01Service extends MyIService<Ac01> {
    List<Ac01Ab01> queryAc01Ab01();
}
