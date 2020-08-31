package com.example.lewjun.service.impl;

import com.example.lewjun.base.MyServiceImpl;
import com.example.lewjun.domain.SysDept;
import com.example.lewjun.domain.SysDeptNode;
import com.example.lewjun.mapper.SysDeptMapper;
import com.example.lewjun.service.SysDeptService;
import com.example.lewjun.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class SysDeptServiceImpl extends MyServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    private final SysUserService sysUserService;

    @Autowired
    public SysDeptServiceImpl(final SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @Override
    public SysDeptNode getSysDeptTrees(final Serializable deptId) {
        final SysDept sysDept = super.getByIdOptional(deptId).orElseThrow(() -> new RuntimeException("部门不存在"));
        final SysDeptNode sysDeptNode = new SysDeptNode();
        sysDeptNode.setId(sysDept.getId());
        sysDeptNode.setName(sysDept.getName());
        sysDeptNode.setParentId(sysDept.getParentId());

        getSubSysDeptList(sysDeptNode);

        return sysDeptNode;
    }

    private void getSubSysDeptList(final SysDeptNode sysDeptNode) {
        final List<SysDeptNode> subSysDeptList = baseMapper.findByParentId(sysDeptNode.getId());
        sysDeptNode.setChildren(subSysDeptList);

        for (final SysDeptNode deptNode : subSysDeptList) {
            getSubSysDeptList(deptNode);
        }
    }

    private boolean existsChildren(final Serializable deptId) {
        return baseMapper.existsChildren(deptId).isPresent();
    }

    @Override
    public boolean removeById(final Serializable id) {
        if (isRoot(id)) {
            throw new RuntimeException("不能删除根部门。");
        }

        if (existsChildren(id)) {
            throw new RuntimeException("删除失败，部门存在下级部门。");
        }

        if (sysUserService.existsByDeptId(id)) {
            throw new RuntimeException("删除失败，部门被用户使用。");
        }

        return super.removeById(id);
    }

    /**
     * 判断是否为根部门
     */
    private boolean isRoot(final Serializable id) {
        return (Long) id == 1L;
    }
}
