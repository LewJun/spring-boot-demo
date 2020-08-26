package com.example.lewjun.service.impl;

import com.example.lewjun.base.MyServiceImpl;
import com.example.lewjun.domain.SysUser;
import com.example.lewjun.mapper.SysUserMapper;
import com.example.lewjun.service.SysUserService;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends MyServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public boolean existsByUsername(final String username) {
        return baseMapper.existsByUsername(username) != 0;
    }

    @Override
    public Long findUserIdByUsername(final String username) {
        return baseMapper.findUserIdByUsername(username)
                .orElseThrow(
                        () -> new RuntimeException("用户名不存在")
                );
    }

    @Override
    public boolean save(final SysUser sysUser) {
        if (existsByUsername(sysUser.getUsername())) {
            throw new RuntimeException("用户名已存在");
        }
        return super.save(sysUser);
    }
}
