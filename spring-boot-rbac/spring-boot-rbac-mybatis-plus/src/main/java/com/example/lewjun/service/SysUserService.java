package com.example.lewjun.service;

import com.example.lewjun.base.MyIService;
import com.example.lewjun.domain.SysUser;
import com.example.lewjun.domain.SysUserLoginDetailsDO;

import java.io.Serializable;

public interface SysUserService extends MyIService<SysUser> {
    /**
     * 根据用户名判断是否存在
     *
     * @param username
     * @return true if exists
     */
    boolean existsByUsername(String username);

    /**
     * 根据用户名查找用户id
     *
     * @param username 用户名
     * @return 用户id
     */
    Long findUserIdByUsername(String username);

    boolean existsByDeptId(Serializable deptId);

    SysUserLoginDetailsDO findByUsername(String username);
}
