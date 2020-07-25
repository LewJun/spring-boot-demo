package com.example.lewjun.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.lewjun.domain.Ab01;
import com.example.lewjun.domain.Ab01Ac01;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Ab01Mapper extends BaseMapper<Ab01> {
    @Select("select * from ab01 where aab002=#{aab002}")
    List<Ab01> queryByAab002(String aab002);

    List<Ab01> queryByAab003(String aab003);

    List<Ab01Ac01> queryAb01Ac01();

    List<Ab01> queryWhere(Ab01 ab01);

    List<Ab01> queryChoose(String words);
}
