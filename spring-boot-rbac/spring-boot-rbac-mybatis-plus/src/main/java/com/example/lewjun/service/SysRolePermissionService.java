package com.example.lewjun.service;

import com.example.lewjun.base.MyIService;
import com.example.lewjun.domain.SysRolePermission;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;

public interface SysRolePermissionService extends MyIService<SysRolePermission> {

    /**
     * 根据权限id查询是否有角色权限关联
     *
     * @param permissionId 权限id
     * @return 1 if exists, otherwise 0
     */
    @Select("SELECT COUNT(1) FROM sys_role_permission t WHERE t.`permission_id` = #{permissionId} LIMIT 1")
    boolean existsRolePermissionByPermissionId(Serializable permissionId);
}
