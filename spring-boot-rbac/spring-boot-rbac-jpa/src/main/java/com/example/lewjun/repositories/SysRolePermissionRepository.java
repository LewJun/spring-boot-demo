package com.example.lewjun.repositories;

import com.example.lewjun.domain.SysRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRolePermissionRepository extends JpaRepository<SysRolePermission, SysRolePermission> {
    List<SysRolePermission> findByPermissionId(Long permissionId);

    int countByPermissionId(Long permissionId);
}
