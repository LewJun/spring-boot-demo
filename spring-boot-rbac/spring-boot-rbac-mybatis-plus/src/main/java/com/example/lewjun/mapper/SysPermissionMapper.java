package com.example.lewjun.mapper;

import com.example.lewjun.base.MyBaseMapper;
import com.example.lewjun.domain.SysPermission;
import com.example.lewjun.domain.SysPermissionWithSubSysPermission;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface SysPermissionMapper extends MyBaseMapper<SysPermission> {
    List<SysPermission> findByRoleId(long roleId);

    List<SysPermission> findSubPermissionById(long id);

    List<SysPermissionWithSubSysPermission> findByRoleIdWithSubPermission(long roleId);

    List<SysPermissionWithSubSysPermission> findByIdWithSubSysPermission(long id);

    @Select("select count(1) from sys_permission t where t.parent_id=#{id} limit 1")
    int existsSubPermissionsById(Serializable id);
}
