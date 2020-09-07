package com.example.lewjun.service.impl;

import com.example.lewjun.base.MyPageInfo;
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
    public SysDeptNode getSysDeptTrees(final Integer deptId) {
        final SysDept sysDept = super.getByIdOptional(deptId).orElseThrow(() -> new RuntimeException("部门不存在"));
        final SysDeptNode sysDeptNode = new SysDeptNode();
        sysDeptNode.setId(sysDept.getId());
        sysDeptNode.setName(sysDept.getName());
        sysDeptNode.setParentId(sysDept.getParentId());

        getSubSysDeptList(sysDeptNode);

        return sysDeptNode;
    }

    private void getSubSysDeptList(final SysDeptNode sysDeptNode) {
        final MyPageInfo<SysDeptNode> pageInfo = baseMapper.findByParentId(new MyPageInfo<>(1, 999), sysDeptNode.getId());
        final List<SysDeptNode> subSysDeptList = pageInfo.getRecords();
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
        return (Integer) id == 1;
    }

    @Override
    public boolean save(final SysDept entity) {
        if (findIdByParentIdAndName(entity.getParentId(), entity.getName())) {
            throw new RuntimeException("同一个部门下的子部门名称不能重复。");
        }
        return super.save(entity);
    }

    @Override
    public boolean updateById(final SysDept entity) {
        if (!baseMapper.findIdByParentIdAndName(entity.getParentId(), entity.getName()).orElse(0).equals(entity.getId())) {
            throw new RuntimeException("同一个部门下的子部门名称不能重复。");
        }
        return super.updateById(entity);
    }

    public boolean findIdByParentIdAndName(final Integer parentId, final String name) {
        return baseMapper.findIdByParentIdAndName(parentId, name).isPresent();
    }
}
