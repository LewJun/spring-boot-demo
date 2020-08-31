package com.example.lewjun.mapper;

import com.example.lewjun.base.MyBaseMapper;
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
    List<SysRole> findByUserId(long userId);

    @Select("select t.id from sys_role t where t.name = #{name} limit 1")
    Optional<Long> existsByName(String name);
}
