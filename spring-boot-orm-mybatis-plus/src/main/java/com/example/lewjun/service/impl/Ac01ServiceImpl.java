package com.example.lewjun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.lewjun.domain.Ac01;
import com.example.lewjun.domain.Ac01Ab01;
import com.example.lewjun.mapper.Ac01Mapper;
import com.example.lewjun.service.Ac01Service;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Ac01ServiceImpl extends ServiceImpl<Ac01Mapper, Ac01> implements Ac01Service {
    @Override
    public List<Ac01Ab01> queryAc01Ab01() {
        return baseMapper.queryAc01Ab01();
    }
}
