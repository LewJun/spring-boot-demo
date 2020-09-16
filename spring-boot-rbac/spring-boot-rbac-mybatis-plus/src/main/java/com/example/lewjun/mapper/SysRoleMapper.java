package com.example.lewjun.mapper;

import com.example.lewjun.base.MyBaseMapper;
import com.example.lewjun.base.MyPageInfo;
import com.example.lewjun.domain.SysRole;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SysRoleMapper extends MyBaseMapper<SysRole> {
    /**
     * 根据用户id查询对应的角色
     *
     * @param userId 用户id
     * @return 角色列表
     */
    MyPageInfo<SysRole> findByUserId(MyPageInfo<?> page, long userId);

    @Select("select t.id from sys_role t where t.name = #{name} limit 1")
    Optional<Integer> existsByName(String name);

    @Select("SELECT sys_role.name FROM sys_role " +
            "JOIN sys_role_permission ON sys_role_permission.`role_id` = sys_role.id " +
            "JOIN sys_permission ON sys_permission.url=#{url} AND sys_permission.id=sys_role_permission.`permission_id`")
    List<String> findRolesByUrl(String url);
}
