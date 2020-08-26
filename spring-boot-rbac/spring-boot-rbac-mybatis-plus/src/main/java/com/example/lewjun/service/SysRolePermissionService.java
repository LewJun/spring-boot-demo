package com.example.lewjun.service;

import com.example.lewjun.base.MyIService;
import com.example.lewjun.domain.SysRolePermission;

import java.io.Serializable;

public interface SysRolePermissionService extends MyIService<SysRolePermission> {
    boolean existsRolePermissionByPermissionId(Serializable permissionId);
}
