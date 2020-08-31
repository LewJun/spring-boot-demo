package com.example.lewjun.service;

import com.example.lewjun.base.MyIService;
import com.example.lewjun.domain.SysRole;

import java.util.List;

public interface SysRoleService extends MyIService<SysRole> {
    /**
     * 根据用户id查询对应的角色
     *
     * @param userId 用户id
     * @return 角色列表
     */
    List<SysRole> findByUserId(long userId);

    boolean existsByName(String name);
}
