package com.example.lewjun.mapper;

import com.example.lewjun.base.MyBaseMapper;
import com.example.lewjun.domain.SysUserRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;
import java.util.Optional;

public interface SysUserRoleMapper extends MyBaseMapper<SysUserRole> {
    /**
     * 根据角色id判断是否有用户和角色绑定
     *
     * @param roleId 角色id
     * @return 1 if exists, otherwise 0
     */
    @Select("SELECT 1 FROM sys_user_role t WHERE t.`role_id` = #{roleId} LIMIT 1;")
    Optional<Integer> existsSysUserRolesByRoleId(Serializable roleId);

    @Delete("delete from sys_user_role where user_id=#{userId} and role_id=#{roleId}")
    int remove(SysUserRole sysUserRole);

    @Select("select 1 from sys_user_role t where t.user_id = #{userId} and t.role_id = #{roleId} limit 1")
    Optional<Integer> existsByUserIdAndRoleId(Long userId, Long roleId);
}
