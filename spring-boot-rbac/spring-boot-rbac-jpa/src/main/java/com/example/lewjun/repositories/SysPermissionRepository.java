package com.example.lewjun.repositories;

import com.example.lewjun.domain.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysPermissionRepository extends JpaRepository<SysPermission, Long> {
    List<SysPermission> findByParentId(Long parentId);
    int countByParentId(Long parentId);
}
