package com.example.lewjun.service.impl;

import com.example.lewjun.domain.SysPermission;
import com.example.lewjun.repositories.SysPermissionRepository;
import com.example.lewjun.service.SysPermissionService;
import com.example.lewjun.service.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysPermissionServiceImpl extends BaseServiceImpl<SysPermission, Long> implements SysPermissionService {
    private final SysRolePermissionService sysRolePermissionService;
    private final SysPermissionRepository sysPermissionRepository;

    @Autowired
    SysPermissionServiceImpl(
            final JpaRepository<SysPermission, Long> jpaRepository,
            final SysPermissionRepository sysPermissionRepository,
            final SysRolePermissionService sysRolePermissionService
    ) {
        super(jpaRepository);
        this.sysRolePermissionService = sysRolePermissionService;
        this.sysPermissionRepository = sysPermissionRepository;
    }

    @Override
    public void deleteById(final Long id) {
        if (countByParentId(id) > 0) {
            throw new RuntimeException("删除失败，权限存在子权限。");
        }

        if (sysRolePermissionService.countByPermissionId(id) == 0) {
            super.deleteById(id);
        } else {
            throw new RuntimeException("删除失败，权限正在被角色使用。");
        }
    }

    @Override
    public void delete(final SysPermission sysPermission) {
        this.deleteById(sysPermission.getId());
    }

    @Override
    public List<SysPermission> findByParentId(final long parentId) {
        return sysPermissionRepository.findByParentId(parentId);
    }

    @Override
    public int countByParentId(final long parentId) {
        return sysPermissionRepository.countByParentId(parentId);
    }
}
