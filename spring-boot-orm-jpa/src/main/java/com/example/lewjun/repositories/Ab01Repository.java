package com.example.lewjun.repositories;

import com.example.lewjun.domain.Ab01;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Ab01Repository extends JpaRepository<Ab01, Integer> {

    List<Ab01> findByAab002(String aab002);

    List<Ab01> findByAab002AndAab003(String aab002, String aab003);

    @Query("from Ab01 t where t.aab002=:aab002")
    List<Ab01> findAb01(String aab002);
}
