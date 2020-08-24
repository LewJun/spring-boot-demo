package com.example.lewjun.service.impl;

import com.example.lewjun.domain.SysRole;
import com.example.lewjun.service.SysRoleService;
import com.example.lewjun.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, Long> implements SysRoleService {
    private final SysUserRoleService sysUserRoleService;

    @Autowired
    SysRoleServiceImpl(
            final JpaRepository<SysRole, Long> jpaRepository,
            final SysUserRoleService sysUserRoleService
    ) {
        super(jpaRepository);
        this.sysUserRoleService = sysUserRoleService;
    }

    @Override
    public void deleteById(final Long id) {
        if (sysUserRoleService.countByRoleId(id) == 0) {
            super.deleteById(id);
        } else {
            throw new RuntimeException("删除失败，角色正在被用户使用。");
        }
    }

    @Override
    public void delete(SysRole sysRole) {
        this.deleteById(sysRole.getId());
    }
}
