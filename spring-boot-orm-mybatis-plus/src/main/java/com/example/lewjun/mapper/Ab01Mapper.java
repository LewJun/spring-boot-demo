package com.example.lewjun.mapper;

import com.example.lewjun.base.MyBaseMapper;
import com.example.lewjun.domain.Ab01;
import com.example.lewjun.domain.Ab01Ac01;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Ab01Mapper extends MyBaseMapper<Ab01> {
    @Select("select * from ab01 where aab002=#{aab002}")
    List<Ab01> queryByAab002(String aab002);

    List<Ab01> queryByAab003(String aab003);

    List<Ab01Ac01> queryAb01Ac01();

    List<Ab01> queryWhere(Ab01 ab01);

    List<Ab01> queryChoose(String words);

    List<Ab01> queryTrim();

    int updateSelective(Ab01 ab01);

    List<Ab01> queryByPks(List<Integer> pks);

    /**
     * 使用foreach批量插入
     *
     * @param ab01s
     */
    int inserts(List<Ab01> ab01s);

    /**
     * 使用trim动态加入values()
     *
     * @param ab01
     */
    int insertSelective(Ab01 ab01);
}
