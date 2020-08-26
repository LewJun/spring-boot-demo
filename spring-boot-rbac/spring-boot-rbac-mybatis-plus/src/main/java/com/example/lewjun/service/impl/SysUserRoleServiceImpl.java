package com.example.lewjun.service.impl;

import com.example.lewjun.base.MyServiceImpl;
import com.example.lewjun.domain.SysUserRole;
import com.example.lewjun.mapper.SysUserRoleMapper;
import com.example.lewjun.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import java.io.Serializable;


@Service
public class SysUserRoleServiceImpl extends MyServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {
    @Override
    public boolean existsSysUserRolesByRoleId(final Serializable roleId) {
        return baseMapper.existsSysUserRolesByRoleId(roleId) != 0;
    }
}
