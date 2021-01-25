package com.example.lewjun.repositories;

import com.example.lewjun.domain.one2many.FatherChild;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FatherChildRepository extends JpaRepository<FatherChild, Integer> {
}
