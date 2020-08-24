package com.example.lewjun.service;

import com.example.lewjun.domain.SysUserRole;

import java.util.List;

public interface SysUserRoleService extends IBaseService<SysUserRole, SysUserRole> {
    List<SysUserRole> findByRoleId(long roleId);

    int countByRoleId(long roleId);
}
