package com.example.lewjun.mapper;

import com.example.lewjun.domain.Ac01;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Ac01Mapper {
    @Select("select * from ac01")
    List<Ac01> queryAll();

    @Insert("insert into ac01(aac002, aac003, aac004, aac005, aac006) values(#{aac002}, #{aac003}, #{aac004}, #{aac005}, #{aac006})")
    // 自动生成主键
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insert(Ac01 ac01);
}
