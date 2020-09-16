package com.example.lewjun.service.impl;

import com.example.lewjun.base.MyServiceImpl;
import com.example.lewjun.common.BussException;
import com.example.lewjun.common.EnumApiResultStatus;
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

    @Transactional(
            rollbackFor = {Exception.class}
    )
    @Override
    public boolean save(final SysRolePermission entity) {
        final Integer roleId = entity.getRoleId();
        if (sysRoleMapper.selectById(roleId) == null) {
            throw BussException.of(EnumApiResultStatus.SYS_ROLE_NOT_EXISTS);
        }

        final Integer permissionId = entity.getPermissionId();
        if (sysPermissionMapper.selectById(permissionId) == null) {
            throw BussException.of(EnumApiResultStatus.SYS_PERMISSION_NOT_EXISTS);
        }
        return super.save(entity);
    }
}
