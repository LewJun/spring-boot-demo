package com.example.lewjun.repositories;

import com.example.lewjun.domain.one2one.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
