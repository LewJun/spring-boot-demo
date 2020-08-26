package com.example.lewjun.service;

import com.example.lewjun.base.MyIService;
import com.example.lewjun.domain.SysRole;

import java.util.List;

public interface SysRoleService extends MyIService<SysRole> {
    List<SysRole> findByUserId(long userId);
}
