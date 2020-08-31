package com.example.lewjun.service.impl;

import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.example.lewjun.base.MyServiceImpl;
import com.example.lewjun.domain.SysUser;
import com.example.lewjun.domain.SysUserLoginDetailsDO;
import com.example.lewjun.mapper.SysUserMapper;
import com.example.lewjun.service.SysUserService;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class SysUserServiceImpl extends MyServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public boolean existsByUsername(final String username) {
        return baseMapper.existsByUsername(username).isPresent();
    }

    @Override
    public Long findUserIdByUsername(final String username) {
        return baseMapper.findUserIdByUsername(username)
                .orElseThrow(
                        () -> new RuntimeException("用户名不存在。")
                );
    }

    @Override
    public boolean existsByDeptId(final Serializable deptId) {
        return baseMapper.existsByDeptId(deptId).isPresent();
    }

    @Override
    public SysUserLoginDetailsDO findByUsername(final String username) {
        return baseMapper.findByUsername(username).orElseThrow(
                () -> new RuntimeException("用户名或密码错误。")
        );
    }

    @Override
    public boolean save(final SysUser sysUser) {
        if (existsByUsername(sysUser.getUsername())) {
            throw new RuntimeException("用户名已存在。");
        }
        return super.save(sysUser);
    }

    @Override
    public boolean removeById(final Serializable id) {
        if (isRoot(id)) {
            throw new RuntimeException("不允许删除超级管理员。");
        }

        return super.removeById(id);
    }

    @Override
    public boolean updateBySelective(final SysUser entity) {
        return SqlHelper.retBool(baseMapper.updateBySelective(entity));
    }

    private boolean isRoot(final Serializable userId) {
        return 1L == (Long) userId;
    }
}
