package com.example.lewjun.service;

import com.example.lewjun.base.MyIService;
import com.example.lewjun.domain.SysUser;

public interface SysUserService extends MyIService<SysUser> {
    /**
     * 根据用户名判断是否存在
     *
     * @param username
     * @return true if exists
     */
    boolean existsByUsername(String username);

    boolean login(SysUser sysUser);

    boolean changePassword(SysUser sysUser, String newPassword);
}
