package com.example.lewjun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.lewjun.domain.Ab01;
import com.example.lewjun.mapper.Ab01Mapper;
import com.example.lewjun.service.Ab01Service;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Ab01ServiceImpl extends ServiceImpl<Ab01Mapper, Ab01> implements Ab01Service {
    @Override
    public List<Ab01> queryByAab002(final String aab002) {
        return getBaseMapper().queryByAab002(aab002);
    }
}
