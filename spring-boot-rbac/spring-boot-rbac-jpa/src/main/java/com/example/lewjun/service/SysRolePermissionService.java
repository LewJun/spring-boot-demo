package com.example.lewjun.service;

import com.example.lewjun.domain.SysRolePermission;

import java.util.List;

public interface SysRolePermissionService extends IBaseService<SysRolePermission, SysRolePermission> {
    List<SysRolePermission> findByPermissionId(long permissionId);

    int countByPermissionId(long permissionId);
}
