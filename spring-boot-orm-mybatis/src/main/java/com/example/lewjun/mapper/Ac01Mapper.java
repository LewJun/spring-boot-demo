package com.example.lewjun.mapper;

import com.example.lewjun.domain.Ac01Ab01;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface Ac01Mapper {
    @Select("select ac01.*, ab01.* from ac01 as ac01 join ab01 as ab01 on ab01.aab001 = ac01.aac006")
    @ResultMap("com.example.lewjun.mapper.Ac01Mapper.Ac01Ab01")
    List<Ac01Ab01> queryByAc01Ab01();
}
