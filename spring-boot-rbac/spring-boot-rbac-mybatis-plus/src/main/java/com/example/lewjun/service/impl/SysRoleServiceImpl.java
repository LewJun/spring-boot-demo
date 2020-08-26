package com.example.lewjun.service.impl;

import com.example.lewjun.base.MyServiceImpl;
import com.example.lewjun.domain.SysRole;
import com.example.lewjun.mapper.SysRoleMapper;
import com.example.lewjun.service.SysRoleService;
import com.example.lewjun.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class SysRoleServiceImpl extends MyServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    private final SysUserRoleService sysUserRoleService;

    @Autowired
    public SysRoleServiceImpl(final SysUserRoleService sysUserRoleService) {
        this.sysUserRoleService = sysUserRoleService;
    }

    @Override
    public List<SysRole> findByUserId(final long userId) {
        return baseMapper.findByUserId(userId);
    }

    @Override
    public boolean removeById(final Serializable id) {
        if (sysUserRoleService.existsSysUserRolesByRoleId(id)) {
            throw new RuntimeException("删除失败，角色正在被用户使用。");
        }
        return super.removeById(id);
    }
}
