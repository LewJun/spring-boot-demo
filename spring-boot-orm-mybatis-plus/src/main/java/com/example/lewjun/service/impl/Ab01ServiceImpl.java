package com.example.lewjun.service.impl;

import com.example.lewjun.base.MyServiceImpl;
import com.example.lewjun.domain.Ab01;
import com.example.lewjun.domain.Ab01Ac01;
import com.example.lewjun.mapper.Ab01Mapper;
import com.example.lewjun.service.Ab01Service;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Ab01ServiceImpl extends MyServiceImpl<Ab01Mapper, Ab01> implements Ab01Service {
    @Override
    public List<Ab01> queryByAab002(final String aab002) {
        return baseMapper.queryByAab002(aab002);
    }

    @Override
    public List<Ab01> queryByAab003(final String aab003) {
        return baseMapper.queryByAab003(aab003);
    }

    @Override
    public List<Ab01Ac01> queryAb01Ac01() {
        return baseMapper.queryAb01Ac01();
    }
}
