package com.example.lewjun.service.impl;

import com.example.lewjun.base.MyServiceImpl;
import com.example.lewjun.common.BussException;
import com.example.lewjun.common.EnumApiResultStatus;
import com.example.lewjun.domain.SysUserRole;
import com.example.lewjun.mapper.SysRoleMapper;
import com.example.lewjun.mapper.SysUserMapper;
import com.example.lewjun.mapper.SysUserRoleMapper;
import com.example.lewjun.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;


@Service
public class SysUserRoleServiceImpl extends MyServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    private final SysUserMapper sysUserMapper;

    private final SysRoleMapper sysRoleMapper;

    @Autowired
    public SysUserRoleServiceImpl(final SysUserMapper sysUserMapper, final SysRoleMapper sysRoleMapper) {
        this.sysUserMapper = sysUserMapper;
        this.sysRoleMapper = sysRoleMapper;
    }

    @Override
    public boolean existsSysUserRolesByRoleId(final Serializable roleId) {
        return baseMapper.existsSysUserRolesByRoleId(roleId).isPresent();
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    @Override
    public boolean save(final SysUserRole entity) {
        final Integer userId = entity.getUserId();
        if (sysUserMapper.selectById(userId) == null) {
            throw BussException.of(EnumApiResultStatus.SYS_USER_NOT_EXISTS);
        }

        final Integer roleId = entity.getRoleId();
        if (sysRoleMapper.selectById(roleId) == null) {
            throw BussException.of(EnumApiResultStatus.SYS_ROLE_NOT_EXISTS);
        }

        return baseMapper.existsByUserIdAndRoleId(entity.getUserId(), entity.getRoleId()).isPresent()
                || super.save(entity);
    }
}
