package com.example.lewjun.service;

import com.example.lewjun.base.MyIService;
import com.example.lewjun.domain.SysDept;
import com.example.lewjun.domain.SysDeptNode;

import java.io.Serializable;

public interface SysDeptService extends MyIService<SysDept> {
    /**
     * 根据部门id查询部门树形对象
     *
     * @param deptId 部门列表
     * @return 部门树形对象
     */
    SysDeptNode getSysDeptTrees(Serializable deptId);
}
