package com.example.lewjun.mapper;

import com.example.lewjun.base.MyBaseMapper;
import com.example.lewjun.domain.SysUserLogin;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SysUserLoginMapper extends MyBaseMapper<SysUserLogin> {
    /**
     * 根据用户id查找密码
     *
     * @param userId 用户id
     * @return 密码
     */
    @Select("select t.password from sys_user_login t where t.user_id=#{userId} limit 1")
    Optional<String> findPasswordByUserId(long userId);

    @Update("update sys_user_login set password = #{password} where user_id = #{userId}")
    int resetPassword(SysUserLogin sysUserLogin);

    @Select("select 1 from sys_user_login where user_id=#{userId} limit 1")
    Optional<Integer> existsByUserId(long userId);
}
