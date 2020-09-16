package com.example.lewjun.service.impl;

import com.example.lewjun.base.MyServiceImpl;
import com.example.lewjun.common.BussException;
import com.example.lewjun.common.EnumApiResultStatus;
import com.example.lewjun.domain.SysDeptRole;
import com.example.lewjun.mapper.SysDeptMapper;
import com.example.lewjun.mapper.SysDeptRoleMapper;
import com.example.lewjun.mapper.SysRoleMapper;
import com.example.lewjun.service.SysDeptRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
public class SysDeptRoleServiceImpl extends MyServiceImpl<SysDeptRoleMapper, SysDeptRole> implements SysDeptRoleService {

    private final SysDeptMapper sysDeptMapper;

    private final SysRoleMapper sysRoleMapper;

    @Autowired
    public SysDeptRoleServiceImpl(final SysDeptMapper sysDeptMapper, final SysRoleMapper sysRoleMapper) {
        this.sysDeptMapper = sysDeptMapper;
        this.sysRoleMapper = sysRoleMapper;
    }

    @Override
    public boolean existsSysDeptRolesByRoleId(final Serializable roleId) {
        return baseMapper.existsSysDeptRolesByRoleId(roleId).isPresent();
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    @Override
    public boolean save(final SysDeptRole entity) {
        final Integer deptId = entity.getDeptId();
        if (sysDeptMapper.selectById(deptId) == null) {
            throw BussException.of(EnumApiResultStatus.SYS_DEPT_NOT_EXISTS);
        }

        final Integer roleId = entity.getRoleId();
        if (sysRoleMapper.selectById(roleId) == null) {
            throw BussException.of(EnumApiResultStatus.SYS_ROLE_NOT_EXISTS);
        }

        return super.save(entity);
    }
}
