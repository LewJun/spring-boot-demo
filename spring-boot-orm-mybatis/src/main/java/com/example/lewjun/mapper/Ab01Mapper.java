package com.example.lewjun.mapper;

import com.example.lewjun.domain.Ab01;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@CacheNamespace(flushInterval = 60000)
@Repository
public interface Ab01Mapper {

    @Insert("insert into ab01(aab002, aab003) values(#{aab002}, #{aab003})")
    // 自动生成主键
    @Options(useGeneratedKeys = true, keyColumn = "aab001", keyProperty = "aab001")
    int insert(Ab01 ab01);

    @Update("update ab01 set aab002=#{aab002}, aab003=#{aab003} where aab001=#{aab001}")
    int update(Ab01 ab01);

    @Delete("delete from ab01 where aab001=#{aab001}")
    int delete(Integer aab001);

    @Select("select * from ab01 where aab001=#{aab001}")
    Ab01 queryByAab001(Integer aab001);

    @Select("select * from ab01 where aab002=#{aab002}")
    List<Ab01> queryByAab002(String aab002);

    @Select("select * from ab01 where aab002=#{aab002} and aab003=#{aab003}")
    List<Ab01> queryByAab002AndAab003(String aab002, String aab003);

    @Select("select * from ab01")
    @ResultMap("com.example.lewjun.mapper.Ab01Mapper.BaseResultMap")
    List<Ab01> queryAll();

    @Select("select aab001 as ab01_aab001, aab002 as ab01_aab002, aab003 as ab01_aab003 from ab01 where aab003=#{aab003} and aab002=#{aab002}")
    @Results(
            {
                    @Result(id = true, column = "ab01_aab001", property = "aab001")
                    , @Result(column = "ab01_aab002", property = "aab002")
                    , @Result(column = "ab01_aab003", property = "aab003")
            }
    )
    List<Ab01> queryByAab003AndAab002(String aab003, String aab002);

    @Select("select aab001 as ab01_aab001, aab002 as ab01_aab002, aab003 as ab01_aab003 from ab01 where aab003=#{aab003}")
    // 使用@Results的方式不方便复用，可以使用@ResultMap来解决
    @ResultMap("com.example.lewjun.mapper.Ab01Mapper.ResultMapOfQueryByAab003")
    List<Ab01> queryByAab003(String aab003);
}
