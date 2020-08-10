package com.example.lewjun.mapper;

import com.example.lewjun.base.MyBaseMapper;
import com.example.lewjun.domain.Ac01;
import com.example.lewjun.domain.Ac01Ab01;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Ac01Mapper extends MyBaseMapper<Ac01> {
    List<Ac01Ab01> queryAc01Ab01();
}
