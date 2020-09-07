package com.example.lewjun.service;

import com.example.lewjun.base.MyIService;
import com.example.lewjun.base.MyPageInfo;
import com.example.lewjun.domain.SysRole;

public interface SysRoleService extends MyIService<SysRole> {
    /**
     * 根据用户id查询对应的角色
     *
     * @param userId 用户id
     * @return 角色列表
     */
    MyPageInfo<SysRole> findByUserId(MyPageInfo<?> page, Integer userId);

    boolean existsByName(String name);
}
