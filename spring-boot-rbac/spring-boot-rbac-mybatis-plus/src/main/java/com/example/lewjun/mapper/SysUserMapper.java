package com.example.lewjun.mapper;

import com.example.lewjun.base.MyBaseMapper;
import com.example.lewjun.domain.SysUser;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserMapper extends MyBaseMapper<SysUser> {
    /**
     * 根据用户名判断是否存在
     *
     * @param username
     * @return 1
     */
    @Select("select count(1) from sys_user t where t.username=#{username} limit 1")
    int existsByUsername(String username);
}
