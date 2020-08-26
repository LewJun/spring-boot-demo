package com.example.lewjun.service.impl;

import com.example.lewjun.base.MyServiceImpl;
import com.example.lewjun.domain.SysRole;
import com.example.lewjun.mapper.SysRoleMapper;
import com.example.lewjun.service.SysRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl extends MyServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Override
    public List<SysRole> findByUserId(final long userId) {
        return baseMapper.findByUserId(userId);
    }
}
