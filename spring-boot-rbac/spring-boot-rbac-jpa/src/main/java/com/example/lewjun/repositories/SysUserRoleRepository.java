package com.example.lewjun.repositories;

import com.example.lewjun.domain.SysUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserRoleRepository extends JpaRepository<SysUserRole, SysUserRole> {
    List<SysUserRole> findByRoleId(Long roleId);

    int countByRoleId(Long roleId);
}
