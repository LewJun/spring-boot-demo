package com.example.lewjun.mapper;

import com.example.lewjun.domain.Yyss;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface YyssMapper {
    @Select("select * from yyss where yyss001=#{yyss001} limit 1")
    Yyss findByYyss001(String yyss001);

    @Select("select * from yyss where yyss007 like concat('%', #{yyss007} ,'%')")
    List<Yyss> findByYyss007(String yyss007);
}
