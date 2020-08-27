package com.example.lewjun.mapper;

import com.example.lewjun.base.MyBaseMapper;
import com.example.lewjun.domain.SysDept;
import com.example.lewjun.domain.SysDeptNode;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysDeptMapper extends MyBaseMapper<SysDept> {
    @Select("select t.id, t.name, t.parent_id from sys_dept t where t.parent_id = #{parentId}")
    List<SysDeptNode> findByParentId(long parentId);
}
