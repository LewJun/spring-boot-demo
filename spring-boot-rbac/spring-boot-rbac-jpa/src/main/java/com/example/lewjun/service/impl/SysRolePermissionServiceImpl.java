package com.example.lewjun.service.impl;

import com.example.lewjun.domain.SysRolePermission;
import com.example.lewjun.repositories.SysRolePermissionRepository;
import com.example.lewjun.service.SysRolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SysRolePermissionServiceImpl extends BaseServiceImpl<SysRolePermission, SysRolePermission> implements SysRolePermissionService {
    private final SysRolePermissionRepository sysRolePermissionRepository;

    @Autowired
    SysRolePermissionServiceImpl(
            final JpaRepository<SysRolePermission, SysRolePermission> jpaRepository,
            final SysRolePermissionRepository sysRolePermissionRepository
    ) {
        super(jpaRepository);

        this.sysRolePermissionRepository = sysRolePermissionRepository;
    }

    @Override
    public SysRolePermission save(final SysRolePermission sysRolePermission) {
        try {
            return super.save(sysRolePermission);
        } catch (final DataIntegrityViolationException e) {
            log.error("【出现异常：】", e);
            throw new RuntimeException("角色或权限不存在");
        }
    }

    @Override
    public List<SysRolePermission> findByPermissionId(final long permissionId) {
        return sysRolePermissionRepository.findByPermissionId(permissionId);
    }

    @Override
    public int countByPermissionId(final long permissionId) {
        return sysRolePermissionRepository.countByPermissionId(permissionId);
    }

}
