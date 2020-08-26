package com.example.lewjun.service.impl;

import com.example.lewjun.base.MyServiceImpl;
import com.example.lewjun.domain.SysPermission;
import com.example.lewjun.domain.SysPermissionWithSubSysPermission;
import com.example.lewjun.mapper.SysPermissionMapper;
import com.example.lewjun.service.SysPermissionService;
import com.example.lewjun.service.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class SysPermissionServiceImpl extends MyServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {
    private final SysRolePermissionService sysRolePermissionService;

    @Autowired
    public SysPermissionServiceImpl(final SysRolePermissionService sysRolePermissionService) {
        this.sysRolePermissionService = sysRolePermissionService;
    }

    @Override
    public List<SysPermission> findByRoleId(final long roleId) {
        return baseMapper.findByRoleId(roleId);
    }

    @Override
    public List<SysPermission> findSubPermissionById(final long id) {
        return baseMapper.findSubPermissionById(id);
    }

    @Override
    public List<SysPermissionWithSubSysPermission> findByRoleIdWithSubPermission(final long roleId) {
        return baseMapper.findByRoleIdWithSubPermission(roleId);
    }

    @Override
    public List<SysPermissionWithSubSysPermission> findByIdWithSubSysPermission(final long id) {
        return baseMapper.findByIdWithSubSysPermission(id);
    }

    @Override
    public boolean existsSubPermissionById(final Serializable id) {
        return baseMapper.existsSubPermissionsById(id) != 0;
    }

    @Override
    public boolean removeById(final Serializable id) {
        if (existsSubPermissionById(id)) {
            throw new RuntimeException("删除失败，权限存在子权限。");
        }

        if (sysRolePermissionService.existsRolePermissionByPermissionId(id)) {
            throw new RuntimeException("删除失败，权限正在被角色使用。");
        }

        return super.removeById(id);
    }
}
