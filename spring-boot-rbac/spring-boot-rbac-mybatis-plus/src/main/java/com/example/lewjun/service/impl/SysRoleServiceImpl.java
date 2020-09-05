package com.example.lewjun.service.impl;

import com.example.lewjun.base.MyPageInfo;
import com.example.lewjun.base.MyServiceImpl;
import com.example.lewjun.domain.SysRole;
import com.example.lewjun.mapper.SysRoleMapper;
import com.example.lewjun.service.SysDeptRoleService;
import com.example.lewjun.service.SysRoleService;
import com.example.lewjun.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
public class SysRoleServiceImpl extends MyServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    private final SysUserRoleService sysUserRoleService;

    private final SysDeptRoleService sysDeptRoleService;

    @Autowired
    public SysRoleServiceImpl(
            final SysUserRoleService sysUserRoleService,
            final SysDeptRoleService sysDeptRoleService
    ) {
        this.sysUserRoleService = sysUserRoleService;
        this.sysDeptRoleService = sysDeptRoleService;
    }

    @Override
    public MyPageInfo<SysRole> findByUserId(final MyPageInfo<?> page, final long userId) {
        return baseMapper.findByUserId(page, userId);
    }

    @Override
    public boolean existsByName(final String name) {
        return baseMapper.existsByName(name).isPresent();
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    @Override
    public boolean save(final SysRole entity) {
        if (existsByName(entity.getName())) {
            throw new RuntimeException("名称已存在");
        }
        return super.save(entity);
    }

    @Override
    public boolean updateById(final SysRole entity) {
        final Long id = baseMapper.existsByName(entity.getName()).orElse(0L);
        if (!id.equals(entity.getId())) {
            throw new RuntimeException("名称已存在");
        }
        return super.updateById(entity);
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    @Override
    public boolean removeById(final Serializable id) {
        if (sysUserRoleService.existsSysUserRolesByRoleId(id)) {
            throw new RuntimeException("删除失败，角色正在被用户使用。");
        }

        if (sysDeptRoleService.existsSysDeptRolesByRoleId(id)) {
            throw new RuntimeException("删除失败，角色正在被部门使用。");
        }

        return super.removeById(id);
    }
}
