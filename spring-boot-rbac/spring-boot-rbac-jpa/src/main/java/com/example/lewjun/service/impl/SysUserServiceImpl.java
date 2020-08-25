package com.example.lewjun.service.impl;

import com.example.lewjun.domain.SysUser;
import com.example.lewjun.repositories.SysUserRepository;
import com.example.lewjun.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, Long> implements SysUserService {
    private final SysUserRepository sysUserRepository;

    @Autowired
    SysUserServiceImpl(
            final JpaRepository<SysUser, Long> jpaRepository,
            final SysUserRepository sysUserRepository
    ) {
        super(jpaRepository);
        this.sysUserRepository = sysUserRepository;
    }

    @Override
    public boolean existsByUsername(final String username) {
        return sysUserRepository.existsByUsername(username);
    }
}
