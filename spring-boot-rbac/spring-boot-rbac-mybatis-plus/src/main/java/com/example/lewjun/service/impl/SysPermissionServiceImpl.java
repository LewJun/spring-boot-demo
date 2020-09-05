package com.example.lewjun.service.impl;

import com.example.lewjun.base.MyPageInfo;
import com.example.lewjun.base.MyServiceImpl;
import com.example.lewjun.domain.SysPermission;
import com.example.lewjun.domain.SysPermissionWithSubSysPermission;
import com.example.lewjun.mapper.SysPermissionMapper;
import com.example.lewjun.service.SysPermissionService;
import com.example.lewjun.service.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
public class SysPermissionServiceImpl extends MyServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {
    private final SysRolePermissionService sysRolePermissionService;

    @Autowired
    public SysPermissionServiceImpl(final SysRolePermissionService sysRolePermissionService) {
        this.sysRolePermissionService = sysRolePermissionService;
    }

    @Override
    public MyPageInfo<SysPermission> findByRoleId(final MyPageInfo<?> page, final long roleId) {
        return baseMapper.findByRoleId(page, roleId);
    }

    @Override
    public MyPageInfo<SysPermission> findSubPermissionByPermissionId(final MyPageInfo<?> page, final long permissionId) {
        return baseMapper.findSubPermissionByPermissionId(page, permissionId);
    }

    @Override
    public MyPageInfo<SysPermissionWithSubSysPermission> findByRoleIdWithSubPermission(final MyPageInfo<?> page, final long roleId) {
        return baseMapper.findByRoleIdWithSubPermission(page, roleId);
    }

    @Override
    public MyPageInfo<SysPermissionWithSubSysPermission> findByIdWithSubSysPermission(final MyPageInfo<?> page, final long id) {
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
            throw new RuntimeException("删除失败，权限存在子权限。");
        }

        if (sysRolePermissionService.existsRolePermissionByPermissionId(id)) {
            throw new RuntimeException("删除失败，权限正在被角色使用。");
        }

        return super.removeById(id);
    }

    @Override
    public boolean save(final SysPermission entity) {
        if (baseMapper.findIdByParentIdAndName(entity.getParentId(), entity.getName()).isPresent()) {
            throw new RuntimeException("权限名称重复");
        }

        if (baseMapper.findIdByParentIdAndUrl(entity.getParentId(), entity.getUrl()).isPresent()) {
            throw new RuntimeException("url地址重复");
        }

        return super.save(entity);
    }

    @Override
    public boolean updateById(final SysPermission entity) {

        final Long id = entity.getId();
        if (!baseMapper.findIdByParentIdAndName(entity.getParentId(), entity.getName()).orElse(0L).equals(id)) {
            throw new RuntimeException("权限名称重复");
        }

        if (!baseMapper.findIdByParentIdAndUrl(entity.getParentId(), entity.getUrl()).orElse(0L).equals(id)) {
            throw new RuntimeException("url地址重复");
        }

        return super.updateById(entity);
    }
}
