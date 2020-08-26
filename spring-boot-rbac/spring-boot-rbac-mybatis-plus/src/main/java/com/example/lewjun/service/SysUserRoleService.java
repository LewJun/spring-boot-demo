package com.example.lewjun.service;

import com.example.lewjun.base.MyIService;
import com.example.lewjun.domain.SysUserRole;

import java.io.Serializable;

public interface SysUserRoleService extends MyIService<SysUserRole> {
    boolean existsSysUserRolesByRoleId(Serializable roleId);
}
