package com.example.lewjun.service;

import com.example.lewjun.domain.SysPermission;

import java.util.List;

public interface SysPermissionService extends IBaseService<SysPermission, Long> {
    List<SysPermission> findByParentId(long parentId);

    int countByParentId(final long parentId);
}
