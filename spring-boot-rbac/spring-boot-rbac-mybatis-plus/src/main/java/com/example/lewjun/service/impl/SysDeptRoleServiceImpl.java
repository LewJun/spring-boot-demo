package com.example.lewjun.service.impl;

import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.example.lewjun.base.MyServiceImpl;
import com.example.lewjun.domain.SysDeptRole;
import com.example.lewjun.mapper.SysDeptRoleMapper;
import com.example.lewjun.service.SysDeptRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
public class SysDeptRoleServiceImpl extends MyServiceImpl<SysDeptRoleMapper, SysDeptRole> implements SysDeptRoleService {

    @Transactional(
            rollbackFor = {Exception.class}
    )
    @Override
    public boolean remove(final SysDeptRole sysDeptRole) {
        if (sysDeptRole == null) return false;
        return SqlHelper.retBool(baseMapper.remove(sysDeptRole));
    }

    @Override
    public boolean existsSysDeptRolesByRoleId(final Serializable roleId) {
        return baseMapper.existsSysDeptRolesByRoleId(roleId).isPresent();
    }

    @Override
    public boolean save(final SysDeptRole entity) {
        if (existsBySysDeptRole(entity)) {
            throw new RuntimeException("部门角色已存在。");
        }
        return super.save(entity);
    }

    private boolean existsBySysDeptRole(final SysDeptRole entity) {
        return baseMapper.existsBySysDeptRole(entity).isPresent();
    }
}