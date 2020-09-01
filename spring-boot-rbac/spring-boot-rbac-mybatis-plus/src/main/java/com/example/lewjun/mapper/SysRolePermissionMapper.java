package com.example.lewjun.mapper;

import com.example.lewjun.base.MyBaseMapper;
import com.example.lewjun.domain.SysRolePermission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface SysRolePermissionMapper extends MyBaseMapper<SysRolePermission> {

    /**
     * 根据权限id查询是否有角色权限关联
     *
     * @param permissionId 权限id
     * @return 1 if exists, otherwise 0
     */
    @Select("SELECT 1 FROM sys_role_permission t WHERE t.`permission_id` = #{permissionId} LIMIT 1")
    Optional<Integer> existsRolePermissionByPermissionId(Serializable permissionId);

    @Delete("delete from sys_role_permission where role_id=#{roleId} and permission_id=#{permissionId}")
    int remove(SysRolePermission sysRolePermission);

    @Select("select 1 from sys_role_permission t where t.role_id=#{roleId} and t.permission_id=#{permissionId} limit 1")
    Optional<Integer> existsBySysRolePermission(SysRolePermission entity);
}
