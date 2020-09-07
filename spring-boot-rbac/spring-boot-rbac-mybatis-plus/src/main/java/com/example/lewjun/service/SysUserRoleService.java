package com.example.lewjun.service;

import com.example.lewjun.base.MyIService;
import com.example.lewjun.domain.SysUserRole;

import java.io.Serializable;

public interface SysUserRoleService extends MyIService<SysUserRole> {
    /**
     * 根据角色id判断是否有用户和角色绑定
     *
     * @param roleId 角色id
     * @return 1 if exists, otherwise 0
     */
    boolean existsSysUserRolesByRoleId(Serializable roleId);

    boolean remove(SysUserRole sysUserRole);
}
