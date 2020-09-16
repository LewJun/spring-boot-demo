package com.example.lewjun.service.impl;

import com.example.lewjun.base.MyPageInfo;
import com.example.lewjun.base.MyServiceImpl;
import com.example.lewjun.common.BussException;
import com.example.lewjun.common.EnumApiResultStatus;
import com.example.lewjun.domain.SysPermission;
import com.example.lewjun.domain.SysPermissionWithSubSysPermission;
import com.example.lewjun.mapper.SysPermissionMapper;
import com.example.lewjun.mapper.SysRolePermissionMapper;
import com.example.lewjun.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
public class SysPermissionServiceImpl extends MyServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    private final SysRolePermissionMapper sysRolePermissionMapper;

    @Autowired
    public SysPermissionServiceImpl(final SysRolePermissionMapper sysRolePermissionMapper) {
        this.sysRolePermissionMapper = sysRolePermissionMapper;
    }

    @Override
    public MyPageInfo<SysPermission> findByRoleId(final MyPageInfo<?> page, final Integer roleId) {
        return baseMapper.findByRoleId(page, roleId);
    }

    @Override
    public MyPageInfo<SysPermission> findSubPermissionByPermissionId(final MyPageInfo<?> page, final Integer permissionId) {
        return baseMapper.findSubPermissionByPermissionId(page, permissionId);
    }

    @Override
    public MyPageInfo<SysPermissionWithSubSysPermission> findByRoleIdWithSubPermission(final MyPageInfo<?> page, final Integer roleId) {
        return baseMapper.findByRoleIdWithSubPermission(page, roleId);
    }

    @Override
    public MyPageInfo<SysPermissionWithSubSysPermission> findByIdWithSubSysPermission(final MyPageInfo<?> page, final Integer id) {
        return baseMapper.findByIdWithSubSysPermission(page, id);
    }

    @Override
    public boolean existsSubPermissionsByPermissionId(final Serializable permissionId) {
        return baseMapper.existsSubPermissionsByPermissionId(permissionId).isPresent();
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    @Override
    public boolean removeById(final Serializable id) {
        if (existsSubPermissionsByPermissionId(id)) {
            throw BussException.of(EnumApiResultStatus.SYS_PERMISSION_HAS_SUB_ERR);
        }

        if (sysRolePermissionMapper.existsRolePermissionByPermissionId(id).isPresent()) {
            throw BussException.of(EnumApiResultStatus.SYS_PERMISSION_ROLE_USED_ERR);
        }

        return super.removeById(id);
    }

    @Override
    public boolean save(final SysPermission entity) {
        if (baseMapper.findIdByParentIdAndName(entity.getParentId(), entity.getName()).isPresent()) {
            throw BussException.of(EnumApiResultStatus.SYS_PERMISSION_NAME_EXISTS);
        }

        if (baseMapper.findIdByParentIdAndUrl(entity.getParentId(), entity.getUrl()).isPresent()) {
            throw BussException.of(EnumApiResultStatus.SYS_PERMISSION_URL_EXISTS);
        }

        return super.save(entity);
    }

    @Override
    public boolean updateById(final SysPermission entity) {

        final Integer id = entity.getId();
        if (getById(id) == null) {
            throw BussException.of(EnumApiResultStatus.CONTENT_NOT_FOUND);
        }

        int ret = baseMapper.findIdByParentIdAndName(entity.getParentId(), entity.getName()).orElse(0);
        if (ret != 0 && ret != id) {
            throw BussException.of(EnumApiResultStatus.SYS_PERMISSION_NAME_EXISTS);
        }

        ret = baseMapper.findIdByParentIdAndUrl(entity.getParentId(), entity.getUrl()).orElse(0);
        if (ret != 0 && ret != id) {
            throw BussException.of(EnumApiResultStatus.SYS_PERMISSION_URL_EXISTS);
        }

        return super.updateById(entity);
    }
}
