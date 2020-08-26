package com.example.lewjun.service.impl;

import com.example.lewjun.base.MyServiceImpl;
import com.example.lewjun.domain.SysPermission;
import com.example.lewjun.domain.SysPermissionWithSubSysPermission;
import com.example.lewjun.mapper.SysPermissionMapper;
import com.example.lewjun.service.SysPermissionService;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class SysPermissionServiceImpl extends MyServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {
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
            throw new RuntimeException("删除失败，存在下级权限。");
        }
        return super.removeById(id);
    }
}
