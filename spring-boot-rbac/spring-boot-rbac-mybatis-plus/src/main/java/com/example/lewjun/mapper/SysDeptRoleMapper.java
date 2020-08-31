package com.example.lewjun.mapper;

import com.example.lewjun.base.MyBaseMapper;
import com.example.lewjun.domain.SysDeptRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface SysDeptRoleMapper extends MyBaseMapper<SysDeptRole> {
    @Delete("delete from sys_dept_role where dept_id=#{deptId} and role_id=#{roleId}")
    int remove(SysDeptRole sysDeptRole);

    @Select("select 1 from sys_dept_role where role_id=#{roleId} limit 1")
    Optional<Integer> existsSysDeptRolesByRoleId(Serializable roleId);
}
