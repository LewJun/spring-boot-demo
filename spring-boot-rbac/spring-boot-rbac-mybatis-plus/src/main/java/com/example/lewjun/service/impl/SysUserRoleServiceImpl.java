package com.example.lewjun.service.impl;

import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.example.lewjun.base.MyServiceImpl;
import com.example.lewjun.domain.SysUserRole;
import com.example.lewjun.mapper.SysUserRoleMapper;
import com.example.lewjun.service.SysUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;


@Service
public class SysUserRoleServiceImpl extends MyServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {
    @Override
    public boolean existsSysUserRolesByRoleId(final Serializable roleId) {
        return baseMapper.existsSysUserRolesByRoleId(roleId).isPresent();
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    @Override
    public boolean remove(final SysUserRole sysUserRole) {
        return sysUserRole != null && SqlHelper.retBool(baseMapper.remove(sysUserRole));
    }

}
