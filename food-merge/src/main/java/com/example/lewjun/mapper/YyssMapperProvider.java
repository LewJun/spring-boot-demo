package com.example.lewjun.mapper;

import com.example.lewjun.domain.vo.YyssQueryParam;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

public class YyssMapperProvider {
    public String findByParam(final YyssQueryParam param) {
        return new SQL(){
            {
                SELECT("*");
                FROM("yyss");

                if (!StringUtils.isEmpty(param.getYyss007())) {
                    WHERE("yyss007 like concat('%',#{yyss007},'%')");
                }

                LIMIT("#{pageNumber}");
                OFFSET("#{offset}");
            }
        }.toString();
    }

    public String countByParam(final YyssQueryParam param) {
        return new SQL(){
            {
                SELECT("count(1) r");
                FROM("yyss");

                if (!StringUtils.isEmpty(param.getYyss007())) {
                    WHERE("yyss007 like concat('%',#{yyss007},'%')");
                }
            }
        }.toString();
    }
}
