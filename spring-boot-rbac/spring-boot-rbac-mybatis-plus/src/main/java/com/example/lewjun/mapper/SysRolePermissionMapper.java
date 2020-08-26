package com.example.lewjun.mapper;

import com.example.lewjun.base.MyBaseMapper;
import com.example.lewjun.domain.SysRolePermission;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface SysRolePermissionMapper extends MyBaseMapper<SysRolePermission> {

    @Select("SELECT COUNT(1) FROM sys_role_permission t WHERE t.`permission_id` = #{permissionId} LIMIT 1")
    int existsRolePermissionByPermissionId(Serializable permissionId);
}
