package com.example.lewjun.service;

import com.example.lewjun.base.MyIService;
import com.example.lewjun.base.MyPageInfo;
import com.example.lewjun.domain.SysPermission;
import com.example.lewjun.domain.SysPermissionWithSubSysPermission;

import java.io.Serializable;

public interface SysPermissionService extends MyIService<SysPermission> {
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
    boolean existsSubPermissionsByPermissionId(Serializable permissionId);

}
