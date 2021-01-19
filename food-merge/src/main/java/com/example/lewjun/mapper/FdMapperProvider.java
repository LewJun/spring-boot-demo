package com.example.lewjun.mapper;

import com.example.lewjun.domain.vo.FdQueryParam;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

public class FdMapperProvider {
    public String findByParam(final FdQueryParam param) {
        return new SQL(){
            {
                SELECT("*");
                FROM("fd");

                if (!StringUtils.isEmpty(param.getFd002())) {
                    WHERE("fd002 like concat('%',#{fd002},'%')");
                }

                LIMIT("#{pageNumber}");
                OFFSET("#{offset}");
            }
        }.toString();
    }

    public String countByParam(final FdQueryParam param) {
        return new SQL(){
            {
                SELECT("count(1) r");
                FROM("fd");

                if (!StringUtils.isEmpty(param.getFd002())) {
                    WHERE("fd002 like concat('%',#{fd002},'%')");
                }
            }
        }.toString();
    }
}
