package com.example.lewjun.mapper;

import com.example.lewjun.base.MyBaseMapper;
import com.example.lewjun.domain.SysUserRole;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;

public interface SysUserRoleMapper extends MyBaseMapper<SysUserRole> {
    /**
     * 根据角色id判断是否有用户和角色绑定
     *
     * @param roleId 角色id
     * @return 1 if exists, otherwise 0
     */
    @Select("SELECT COUNT(1) FROM sys_user_role t WHERE t.`role_id` = #{roleId} LIMIT 1;")
    int existsSysUserRolesByRoleId(Serializable roleId);
}
