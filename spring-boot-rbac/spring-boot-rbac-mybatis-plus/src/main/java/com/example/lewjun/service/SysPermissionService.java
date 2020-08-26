package com.example.lewjun.service;

import com.example.lewjun.base.MyIService;
import com.example.lewjun.domain.SysPermission;
import com.example.lewjun.domain.SysPermissionWithSubSysPermission;

import java.io.Serializable;
import java.util.List;

public interface SysPermissionService extends MyIService<SysPermission> {
    List<SysPermission> findByRoleId(long roleId);

    List<SysPermission> findSubPermissionById(long id);

    List<SysPermissionWithSubSysPermission> findByRoleIdWithSubPermission(long roleId);

    List<SysPermissionWithSubSysPermission> findByIdWithSubSysPermission(long id);

    boolean existsSubPermissionById(Serializable id);
}
