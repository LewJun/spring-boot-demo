package com.example.lewjun.mapper;

import com.example.lewjun.base.MyBaseMapper;
import com.example.lewjun.domain.SysDept;
import com.example.lewjun.domain.SysDeptNode;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public interface SysDeptMapper extends MyBaseMapper<SysDept> {
    @Select("select t.id, t.name, t.parent_id from sys_dept t where t.parent_id = #{parentId}")
    List<SysDeptNode> findByParentId(long parentId);

    @Select("select 1 from sys_dept t where t.parent_id = #{deptId} limit 1")
    Optional<Integer> existsChildren(Serializable deptId);

    @Select("select t.id from sys_dept t where t.parent_id = #{parentId} and t.`name` = #{name} limit 1")
    Optional<Long> findIdByParentIdAndName(Long parentId, String name);
}
