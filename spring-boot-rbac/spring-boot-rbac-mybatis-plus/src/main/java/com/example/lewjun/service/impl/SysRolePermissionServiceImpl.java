package com.example.lewjun.service.impl;

import com.example.lewjun.base.MyServiceImpl;
import com.example.lewjun.domain.SysRolePermission;
import com.example.lewjun.mapper.SysRolePermissionMapper;
import com.example.lewjun.service.SysRolePermissionService;
import org.springframework.stereotype.Service;

import java.io.Serializable;


@Service
public class SysRolePermissionServiceImpl extends MyServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {
    @Override
    public boolean existsRolePermissionByPermissionId(final Serializable permissionId) {
        return baseMapper.existsRolePermissionByPermissionId(permissionId) != 0;
    }
}
