package com.example.lewjun.mapper;

import com.example.lewjun.domain.Yyss;
import com.example.lewjun.domain.vo.YyssQueryParam;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface YyssMapper {
    @Select("select * from yyss where yyss001=#{yyss001} limit 1")
    Yyss findByYyss001(String yyss001);

    @SelectProvider(type = YyssMapperProvider.class, method = "findByParam")
    List<Yyss> findByParam(YyssQueryParam param);

    @SelectProvider(type = YyssMapperProvider.class, method = "countByParam")
    int countByParam(YyssQueryParam param);
}
