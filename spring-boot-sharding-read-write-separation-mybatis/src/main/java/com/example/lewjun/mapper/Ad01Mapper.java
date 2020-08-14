package com.example.lewjun.mapper;

import com.example.lewjun.domain.Ad01;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface Ad01Mapper {

    @Insert("insert into ad01(aad001) values(#{aad001})")
    int insert(Ad01 ad01);

    @Select("select * from ad01")
    List<Ad01> queryAll();

    @Delete("truncate table ad01")
    void truncate();
}
