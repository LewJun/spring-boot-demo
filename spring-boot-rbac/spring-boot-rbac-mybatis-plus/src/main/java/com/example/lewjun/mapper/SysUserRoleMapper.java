package com.example.lewjun.mapper;

import com.example.lewjun.base.MyBaseMapper;
import com.example.lewjun.domain.SysUserRole;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;

public interface SysUserRoleMapper extends MyBaseMapper<SysUserRole> {
    @Select("SELECT COUNT(1) FROM sys_user_role t WHERE t.`role_id` = #{roleId} LIMIT 1;")
    int existsSysUserRolesByRoleId(Serializable roleId);
}
