package com.example.lewjun.mapper;

import com.example.lewjun.base.MyBaseMapper;
import com.example.lewjun.domain.SysUser;
import com.example.lewjun.domain.SysUserLoginDetailsDO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface SysUserMapper extends MyBaseMapper<SysUser> {
    /**
     * 根据用户名判断是否存在
     *
     * @param username 用户名
     * @return 1 if exists, otherwise 0
     */
    @Select("select 1 from sys_user t where t.username=#{username} limit 1")
    Optional<Integer> existsByUsername(String username);

    /**
     * 根据用户名查找用户id
     *
     * @param username 用户名
     * @return 用户id
     */
    @Select("select t.id from sys_user t where t.username=#{username} limit 1")
    Optional<Long> findUserIdByUsername(String username);

    @Select("select 1 from sys_user t where t.dept_id=#{deptId} limit 1")
    Optional<Integer> existsByDeptId(Serializable deptId);

    @Select("SELECT sys_user.id, sys_user.`username`, sys_user.`nickname`, sys_user.`email`, sys_user_login.`password` FROM sys_user"
            + " JOIN sys_user_login ON sys_user_login.`user_id` = sys_user.`id`"
            + " WHERE sys_user.`username` = #{username} LIMIT 1"
    )
    Optional<SysUserLoginDetailsDO> findByUsername(String username);

    @Override
    int updateBySelective(final SysUser entity);
}
