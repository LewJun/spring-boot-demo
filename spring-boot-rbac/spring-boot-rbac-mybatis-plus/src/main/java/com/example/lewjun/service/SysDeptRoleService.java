package com.example.lewjun.service;

import com.example.lewjun.base.MyIService;
import com.example.lewjun.domain.SysDeptRole;

import java.io.Serializable;

public interface SysDeptRoleService extends MyIService<SysDeptRole> {
    boolean existsSysDeptRolesByRoleId(Serializable roleId);
}
