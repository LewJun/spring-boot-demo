package com.example.lewjun.repositories;

import com.example.lewjun.domain.one2many2.Son;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SonRepository extends JpaRepository<Son, Integer> {
}
