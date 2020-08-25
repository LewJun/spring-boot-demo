package com.example.lewjun.service;

import com.example.lewjun.domain.SysUser;

public interface SysUserService extends IBaseService<SysUser, Long> {
    /**
     * 根据用户名判断是否存在
     *
     * @param username
     * @return true if exists
     */
    boolean existsByUsername(String username);
}
