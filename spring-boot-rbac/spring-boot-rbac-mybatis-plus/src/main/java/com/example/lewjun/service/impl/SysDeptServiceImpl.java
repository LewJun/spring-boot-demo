package com.example.lewjun.service.impl;

import com.example.lewjun.base.MyServiceImpl;
import com.example.lewjun.domain.SysDept;
import com.example.lewjun.domain.SysDeptNode;
import com.example.lewjun.mapper.SysDeptMapper;
import com.example.lewjun.service.SysDeptService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysDeptServiceImpl extends MyServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    @Override
    public SysDeptNode getSysDeptTrees(final long deptId) {
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
}
