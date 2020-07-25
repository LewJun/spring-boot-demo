package com.example.lewjun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.lewjun.domain.Ac01;
import com.example.lewjun.domain.Ac01Ab01;

import java.util.List;

public interface Ac01Service extends IService<Ac01> {
    List<Ac01Ab01> queryAc01Ab01();
}
