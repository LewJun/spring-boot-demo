package com.example.lewjun.service.impl;

import com.example.lewjun.base.MyPageInfo;
import com.example.lewjun.base.MyServiceImpl;
import com.example.lewjun.domain.SysRole;
import com.example.lewjun.mapper.SysDeptRoleMapper;
import com.example.lewjun.mapper.SysRoleMapper;
import com.example.lewjun.mapper.SysUserRoleMapper;
import com.example.lewjun.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
public class SysRoleServiceImpl extends MyServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    private final SysUserRoleMapper sysUserRoleMapper;

    private final SysDeptRoleMapper sysDeptRoleMapper;

    @Autowired
    public SysRoleServiceImpl(final SysUserRoleMapper sysUserRoleMapper, final SysDeptRoleMapper sysDeptRoleMapper) {
        this.sysUserRoleMapper = sysUserRoleMapper;
        this.sysDeptRoleMapper = sysDeptRoleMapper;
    }


    @Override
    public MyPageInfo<SysRole> findByUserId(final MyPageInfo<?> page, final Integer userId) {
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
        if (getById(entity.getId()) == null) {
            throw new RuntimeException("资源不存在。");
        }
        final int ret = baseMapper.existsByName(entity.getName()).orElse(0);
        if (ret != 0 && ret != entity.getId()) {
            throw new RuntimeException("名称已存在。");
        }

        return super.updateById(entity);
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    @Override
    public boolean removeById(final Serializable id) {
        if (sysUserRoleMapper.existsSysUserRolesByRoleId(id).isPresent()) {
            throw new RuntimeException("删除失败，角色正在被用户使用。");
        }

        if (sysDeptRoleMapper.existsSysDeptRolesByRoleId(id).isPresent()) {
            throw new RuntimeException("删除失败，角色正在被部门使用。");
        }

        return super.removeById(id);
    }
}
