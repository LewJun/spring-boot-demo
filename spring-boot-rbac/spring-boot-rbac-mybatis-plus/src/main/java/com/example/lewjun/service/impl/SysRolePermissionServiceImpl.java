package com.example.lewjun.service.impl;

import com.example.lewjun.base.MyServiceImpl;
import com.example.lewjun.domain.SysRolePermission;
import com.example.lewjun.mapper.SysPermissionMapper;
import com.example.lewjun.mapper.SysRoleMapper;
import com.example.lewjun.mapper.SysRolePermissionMapper;
import com.example.lewjun.service.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;


@Service
public class SysRolePermissionServiceImpl extends MyServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {

    private final SysRoleMapper sysRoleMapper;
    private final SysPermissionMapper sysPermissionMapper;

    @Autowired
    public SysRolePermissionServiceImpl(final SysRoleMapper sysRoleMapper, final SysPermissionMapper sysPermissionMapper) {
        this.sysRoleMapper = sysRoleMapper;
        this.sysPermissionMapper = sysPermissionMapper;
    }

    @Override
    public boolean existsRolePermissionByPermissionId(final Serializable permissionId) {
        return baseMapper.existsRolePermissionByPermissionId(permissionId).isPresent();
    }

    private boolean existsBySysRolePermission(final SysRolePermission entity) {
        return baseMapper.existsBySysRolePermission(entity).isPresent();
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    @Override
    public boolean save(final SysRolePermission entity) {
        final Integer roleId = entity.getRoleId();
        if (sysRoleMapper.selectById(roleId) == null) {
            throw new RuntimeException("所选角色不存在。");
        }

        final Integer permissionId = entity.getPermissionId();
        if (sysPermissionMapper.selectById(permissionId) == null) {
            throw new RuntimeException("所选权限不存在。");
        }
        return super.save(entity);
    }
}
