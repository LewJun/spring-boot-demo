package com.example.lewjun.repositories;

import com.example.lewjun.domain.one2many.Father;
import com.example.lewjun.domain.one2one_middle.Wife;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FatherRepository extends JpaRepository<Father, Integer> {
}
