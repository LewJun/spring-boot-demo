package com.example.lewjun.service.impl;

import com.example.lewjun.base.MyServiceImpl;
import com.example.lewjun.common.BussException;
import com.example.lewjun.common.EnumApiResultStatus;
import com.example.lewjun.domain.SysUser;
import com.example.lewjun.domain.SysUserLoginDetailsDO;
import com.example.lewjun.mapper.SysDeptMapper;
import com.example.lewjun.mapper.SysUserMapper;
import com.example.lewjun.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class SysUserServiceImpl extends MyServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private final SysDeptMapper sysDeptMapper;

    @Autowired
    public SysUserServiceImpl(final SysDeptMapper sysDeptMapper) {
        this.sysDeptMapper = sysDeptMapper;
    }

    @Override
    public boolean existsByUsername(final String username) {
        return baseMapper.existsByUsername(username).isPresent();
    }

    @Override
    public Integer findUserIdByUsername(final String username) {
        return baseMapper.findUserIdByUsername(username).orElse(0);
    }

    @Override
    public boolean existsByDeptId(final Serializable deptId) {
        return baseMapper.existsByDeptId(deptId).isPresent();
    }

    @Override
    public SysUserLoginDetailsDO findByUsername(final String username) {
        return baseMapper.findByUsername(username).orElseThrow(
                () -> BussException.of(EnumApiResultStatus.SYS_USER_USERNAME_OR_PASSWORD_WRONG_ERR)
        );
    }

    @Override
    public boolean save(final SysUser sysUser) {
        if (existsByUsername(sysUser.getUsername())) {
            throw BussException.of(EnumApiResultStatus.SYS_USER_USERNAME_EXISTS);
        }

        final Integer deptId = sysUser.getDeptId();
        if (deptId != null && sysDeptMapper.selectById(deptId) == null) {
            throw BussException.of(EnumApiResultStatus.SYS_DEPT_NOT_EXISTS);
        }
        return super.save(sysUser);
    }

    @Override
    public boolean removeById(final Serializable id) {
        if (isRoot(id)) {
            throw BussException.of(EnumApiResultStatus.SYS_USER_REMOVE_ROOT_ERR);
        }

        return super.removeById(id);
    }

    @Override
    public boolean updateById(final SysUser entity) {
        final Integer id = entity.getId();
        if (getById(id) == null) {
            throw BussException.of(EnumApiResultStatus.CONTENT_NOT_FOUND);
        }

        final String username = entity.getUsername();
        if (username != null) {
            final int userId = findUserIdByUsername(username);
            if (userId != 0 && userId != id) {
                throw BussException.of(EnumApiResultStatus.SYS_USER_USERNAME_EXISTS);
            }
        }

        final Integer deptId = entity.getDeptId();
        if (deptId != null && sysDeptMapper.selectById(deptId) == null) {
            throw BussException.of(EnumApiResultStatus.SYS_DEPT_NOT_EXISTS);
        }

        return super.updateById(entity);
    }

    private boolean isRoot(final Serializable userId) {
        return 1 == (Integer) userId;
    }
}
