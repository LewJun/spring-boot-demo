package com.example.lewjun.service.impl;

import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.example.lewjun.base.MyServiceImpl;
import com.example.lewjun.domain.SysRolePermission;
import com.example.lewjun.mapper.SysRolePermissionMapper;
import com.example.lewjun.service.SysRolePermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;


@Service
public class SysRolePermissionServiceImpl extends MyServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {
    @Override
    public boolean existsRolePermissionByPermissionId(final Serializable permissionId) {
        return baseMapper.existsRolePermissionByPermissionId(permissionId).isPresent();
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    @Override
    public boolean remove(final SysRolePermission sysRolePermission) {
        if (sysRolePermission == null) return false;

        return SqlHelper.retBool(baseMapper.remove(sysRolePermission));
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    @Override
    public boolean save(final SysRolePermission entity) {
        if (existsBySysRolePermission(entity)) {
            throw new RuntimeException("角色权限已存在。");
        }

        return super.save(entity);
    }

    private boolean existsBySysRolePermission(final SysRolePermission entity) {
        return baseMapper.existsBySysRolePermission(entity).isPresent();
    }
}
