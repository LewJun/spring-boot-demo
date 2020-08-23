package com.example.lewjun.service.impl;

import com.example.lewjun.domain.SysUser;
import com.example.lewjun.repositories.SysUserRepository;
import com.example.lewjun.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    SysUserRepository sysUserRepository;

    @Override
    public SysUser save(final SysUser sysUser) {
        return sysUserRepository.save(sysUser);
    }
}
