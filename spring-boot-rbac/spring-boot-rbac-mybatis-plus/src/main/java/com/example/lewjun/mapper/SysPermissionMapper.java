package com.example.lewjun.mapper;

import com.example.lewjun.base.MyBaseMapper;
import com.example.lewjun.base.MyPageInfo;
import com.example.lewjun.domain.SysPermission;
import com.example.lewjun.domain.SysPermissionWithSubSysPermission;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface SysPermissionMapper extends MyBaseMapper<SysPermission> {
    /**
     * 根据角色id查询权限列表
     *
     * @param roleId 角色id
     * @return 权限列表
     */
    MyPageInfo<SysPermission> findByRoleId(MyPageInfo<?> page, long roleId);

    /**
     * 根据id查找对应的子权限
     *
     * @param permissionId 权限id
     * @return 权限列表
     */
    MyPageInfo<SysPermission> findSubPermissionByPermissionId(MyPageInfo<?> page, long permissionId);

    /**
     * 根据角色id查找权限及子权限
     *
     * @param roleId 角色id
     * @return 权限及子权限列表
     */
    MyPageInfo<SysPermissionWithSubSysPermission> findByRoleIdWithSubPermission(MyPageInfo<?> page, long roleId);

    /**
     * 根据权限id查找权限及子权限
     *
     * @param permissionId 权限id
     * @return 权限及子权限列表
     */
    MyPageInfo<SysPermissionWithSubSysPermission> findByIdWithSubSysPermission(MyPageInfo<?> page, long permissionId);

    /**
     * 根据权限Id判断是否存在子权限
     *
     * @param permissionId 权限id
     * @return 1 if exists, otherwise 0
     */
    @Select("select 1 from sys_permission t where t.parent_id=#{id} limit 1")
    Optional<Integer> existsSubPermissionsByPermissionId(Serializable permissionId);

    @Select("select t.id from sys_permission t where t.parent_id = #{parentId} and t.name = #{name} limit 1")
    Optional<Long> findIdByParentIdAndName(Long parentId, String name);

    @Select("select t.id from sys_permission t where t.parent_id = #{parentId} and t.url = #{url} limit 1")
    Optional<Long> findIdByParentIdAndUrl(Long parentId, String url);
}
