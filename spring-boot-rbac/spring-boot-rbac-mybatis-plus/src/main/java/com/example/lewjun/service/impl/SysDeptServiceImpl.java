package com.example.lewjun.service.impl;

import com.example.lewjun.base.MyPageInfo;
import com.example.lewjun.base.MyServiceImpl;
import com.example.lewjun.domain.SysDept;
import com.example.lewjun.domain.SysDeptNode;
import com.example.lewjun.mapper.SysDeptMapper;
import com.example.lewjun.mapper.SysUserMapper;
import com.example.lewjun.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class SysDeptServiceImpl extends MyServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    private final SysUserMapper sysUserMapper;

    @Autowired
    public SysDeptServiceImpl(final SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
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

        if (sysUserMapper.existsByDeptId(id).isPresent()) {
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
        final Integer parentId = entity.getParentId();
        if (baseMapper.selectById(parentId) == null) {
            throw new RuntimeException("父级部门不存在。");
        }
        if (findIdByParentIdAndName(parentId, entity.getName())) {
            throw new RuntimeException("同一个部门下的子部门名称不能重复。");
        }
        return super.save(entity);
    }

    @Override
    public boolean updateById(final SysDept entity) {
        if (getById(entity.getId()) == null) {
            throw new RuntimeException("资源不存在。");
        }
        final int ret = baseMapper.findIdByParentIdAndName(entity.getParentId(), entity.getName()).orElse(0);
        if (ret == 0 || ret == entity.getId()) {
            return super.updateById(entity);
        }

        throw new RuntimeException("同一个部门下的子部门名称不能重复。");
    }

    public boolean findIdByParentIdAndName(final Integer parentId, final String name) {
        return baseMapper.findIdByParentIdAndName(parentId, name).isPresent();
    }
}
