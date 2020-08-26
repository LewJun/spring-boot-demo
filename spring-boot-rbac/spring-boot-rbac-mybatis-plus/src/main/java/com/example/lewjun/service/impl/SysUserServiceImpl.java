package com.example.lewjun.service.impl;

import com.example.lewjun.base.MyServiceImpl;
import com.example.lewjun.domain.SysUser;
import com.example.lewjun.mapper.SysUserMapper;
import com.example.lewjun.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends MyServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    SysUserServiceImpl(final PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean existsByUsername(final String username) {
        return baseMapper.existsByUsername(username) != 0;
    }

    @Override
    public boolean login(final SysUser sysUser) {
        return passwordMatches(sysUser);
    }

    private boolean passwordMatches(final SysUser sysUser) {
        final String password = findByUsername(sysUser.getUsername());
        return passwordEncoder.matches(sysUser.getPassword(), password);
    }

    @Override
    public boolean changePassword(final SysUser sysUser, final String newPassword) {
        if (existsByUsername(sysUser.getUsername())) {
            throw new RuntimeException("用户名已存在");
        }

        if (!passwordMatches(sysUser)) {
            throw new RuntimeException("用户名和密码不匹配");
        }

        setPassword(sysUser, newPassword);

        return super.updateById(sysUser);
    }

    private void setPassword(final SysUser sysUser, final String password) {
        sysUser.setPassword(passwordEncoder.encode(password));
    }

    private String findByUsername(final String username) {
        return baseMapper.findPasswordByUsername(username);
    }

    @Override
    public boolean save(final SysUser sysUser) {
        if (existsByUsername(sysUser.getUsername())) {
            throw new RuntimeException("用户名已存在");
        }

        setPassword(sysUser, sysUser.getPassword());

        return super.save(sysUser);
    }

    @Override
    public boolean updateById(final SysUser sysUser) {
        throw new RuntimeException("非法操作");
    }
}
