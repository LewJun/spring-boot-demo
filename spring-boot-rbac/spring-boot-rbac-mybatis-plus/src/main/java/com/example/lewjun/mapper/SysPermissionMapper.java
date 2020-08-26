package com.example.lewjun.mapper;

import com.example.lewjun.base.MyBaseMapper;
import com.example.lewjun.domain.SysPermission;
import com.example.lewjun.domain.SysPermissionWithSubSysPermission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysPermissionMapper extends MyBaseMapper<SysPermission> {
    List<SysPermission> findByRoleId(long roleId);

    List<SysPermission> findSubPermissionById(long id);

    List<SysPermissionWithSubSysPermission> findByRoleIdWithSubPermission(long roleId);

    List<SysPermissionWithSubSysPermission> findByIdWithSubSysPermission(long id);
}
