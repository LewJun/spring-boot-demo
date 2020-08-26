package com.example.lewjun.service.impl;

import com.example.lewjun.base.MyServiceImpl;
import com.example.lewjun.domain.SysPermission;
import com.example.lewjun.domain.SysPermissionWithSubSysPermission;
import com.example.lewjun.mapper.SysPermissionMapper;
import com.example.lewjun.service.SysPermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysPermissionServiceImpl extends MyServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {
    @Override
    public List<SysPermission> findByRoleId(long roleId) {
        return baseMapper.findByRoleId(roleId);
    }

    @Override
    public List<SysPermission> findSubPermissionById(long id) {
        return baseMapper.findSubPermissionById(id);
    }

    @Override
    public List<SysPermissionWithSubSysPermission> findByRoleIdWithSubPermission(long roleId) {
        return baseMapper.findByRoleIdWithSubPermission(roleId);
    }

    @Override
    public List<SysPermissionWithSubSysPermission> findByIdWithSubSysPermission(long id) {
        return baseMapper.findByIdWithSubSysPermission(id);
    }
}
