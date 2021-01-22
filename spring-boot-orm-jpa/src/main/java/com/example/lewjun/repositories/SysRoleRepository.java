package com.example.lewjun.repositories;

import com.example.lewjun.domain.many2many.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysRoleRepository extends JpaRepository<SysRole, Integer> {
}
