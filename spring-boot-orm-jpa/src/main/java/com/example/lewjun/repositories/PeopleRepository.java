package com.example.lewjun.repositories;

import com.example.lewjun.domain.one2one.People;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleRepository extends JpaRepository<People, Integer> {
}
