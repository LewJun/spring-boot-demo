package com.example.lewjun.repositories;

import com.example.lewjun.domain.one2many.Child;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildRepository extends JpaRepository<Child, Integer> {
}
