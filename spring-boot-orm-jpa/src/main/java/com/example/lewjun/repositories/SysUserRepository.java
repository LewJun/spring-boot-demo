package com.example.lewjun.repositories;

import com.example.lewjun.domain.many2many.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserRepository extends JpaRepository<SysUser, Integer> {
}
