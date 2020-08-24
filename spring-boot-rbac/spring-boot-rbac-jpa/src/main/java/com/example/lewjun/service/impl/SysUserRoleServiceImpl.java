package com.example.lewjun.service.impl;

import com.example.lewjun.domain.SysUserRole;
import com.example.lewjun.repositories.SysUserRoleRepository;
import com.example.lewjun.service.SysUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole, SysUserRole> implements SysUserRoleService {

    private final SysUserRoleRepository sysUserRoleRepository;

    @Autowired
    SysUserRoleServiceImpl(
            final JpaRepository<SysUserRole, SysUserRole> jpaRepository,
            final SysUserRoleRepository sysUserRoleRepository
    ) {
        super(jpaRepository);
        this.sysUserRoleRepository = sysUserRoleRepository;
    }

    @Override
    public SysUserRole save(final SysUserRole sysUserRole) {
        try {
            return super.save(sysUserRole);
        } catch (final DataIntegrityViolationException e) {
            log.error("【出现异常：】", e);
            throw new RuntimeException("用户或角色不存在");
        }
    }

    @Override
    public List<SysUserRole> findByRoleId(final long roleId) {
        return sysUserRoleRepository.findByRoleId(roleId);
    }

    @Override
    public int countByRoleId(final long roleId) {
        return sysUserRoleRepository.countByRoleId(roleId);
    }
}
