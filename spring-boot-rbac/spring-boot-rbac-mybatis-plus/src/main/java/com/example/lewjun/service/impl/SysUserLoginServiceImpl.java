package com.example.lewjun.service.impl;

import com.example.lewjun.base.MyServiceImpl;
import com.example.lewjun.domain.SysUserLogin;
import com.example.lewjun.mapper.SysUserLoginMapper;
import com.example.lewjun.service.SysUserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SysUserLoginServiceImpl extends MyServiceImpl<SysUserLoginMapper, SysUserLogin> implements SysUserLoginService {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    SysUserLoginServiceImpl(final PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean login(final SysUserLogin sysUserLogin) {
        return passwordMatches(sysUserLogin);
    }

    @Override
    public boolean changePassword(final SysUserLogin sysUserLogin, final String newPassword) {
        if (!passwordMatches(sysUserLogin)) {
            throw new RuntimeException("用户名和密码不匹配");
        }

        setPassword(sysUserLogin, newPassword);

        return super.updateById(sysUserLogin);
    }

    private void setPassword(final SysUserLogin sysUserLogin, final String password) {
        sysUserLogin.setPassword(passwordEncoder.encode(password));
    }

    private String findPasswordByUserId(final long userId) {
        return baseMapper.findPasswordByUserId(userId).orElseThrow(() -> new RuntimeException("用户名或密码错误。"));
    }

    private boolean passwordMatches(final SysUserLogin sysUserLogin) {
        final String password = findPasswordByUserId(sysUserLogin.getUserId());
        return passwordEncoder.matches(sysUserLogin.getPassword(), password);
    }
}
