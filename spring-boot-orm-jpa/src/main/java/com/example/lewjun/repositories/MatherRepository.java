package com.example.lewjun.repositories;

import com.example.lewjun.domain.one2many2.Mather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatherRepository extends JpaRepository<Mather, Integer> {
}
