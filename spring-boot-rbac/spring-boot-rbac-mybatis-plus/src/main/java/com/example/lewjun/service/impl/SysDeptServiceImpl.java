package com.example.lewjun.service.impl;

import com.example.lewjun.base.MyPageInfo;
import com.example.lewjun.base.MyServiceImpl;
import com.example.lewjun.common.BussException;
import com.example.lewjun.common.EnumApiResultStatus;
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
        final SysDept sysDept = super.getByIdOptional(deptId).orElseThrow(() -> BussException.of(EnumApiResultStatus.SYS_DEPT_NOT_EXISTS));
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
            throw BussException.of(EnumApiResultStatus.SYS_DEPT_REMOVE_ROOT_ERR);
        }

        if (existsChildren(id)) {
            throw BussException.of(EnumApiResultStatus.SYS_DEPT_HAS_SUB_ERR);
        }

        if (sysUserMapper.existsByDeptId(id).isPresent()) {

            throw BussException.of(EnumApiResultStatus.SYS_DEPT_USER_USED_ERR);

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
            throw BussException.of(EnumApiResultStatus.SYS_DEPT_PARENT_DEPT_NOT_EXISTS);
        }
        if (findIdByParentIdAndName(parentId, entity.getName())) {
            throw BussException.of(EnumApiResultStatus.SYS_DEPT_DUPLICATE_IN_SAME_DEPT);
        }
        return super.save(entity);
    }

    @Override
    public boolean updateById(final SysDept entity) {
        if (getById(entity.getId()) == null) {
            throw BussException.of(EnumApiResultStatus.CONTENT_NOT_FOUND);
        }

        final int ret = baseMapper.findIdByParentIdAndName(entity.getParentId(), entity.getName()).orElse(0);
        if (ret != 0 && ret != entity.getId()) {
            throw BussException.of(EnumApiResultStatus.SYS_DEPT_DUPLICATE_IN_SAME_DEPT);
        }

        return super.updateById(entity);
    }

    public boolean findIdByParentIdAndName(final Integer parentId, final String name) {
        return baseMapper.findIdByParentIdAndName(parentId, name).isPresent();
    }
}
