package com.example.lewjun.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserMapper {
    @Select("select t.password from sys_user t where t.username=#{username} and t.status = 1")
    String findByUsername(String username);
}
