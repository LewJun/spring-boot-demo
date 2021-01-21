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

                final Integer duizhao = param.getDuizhao();
                if (duizhao != null) {
                    // 未对照
                    if (duizhao == 0) {
                        WHERE("yyss001 is null");
                    } else if (duizhao == 1) {
                        WHERE("yyss001 is not null");
                    } else {

                    }
                }

                LIMIT("#{pageNumber}");
                OFFSET("#{offset}");
            }
        }.toString();
    }

    public String countByParam(final FdQueryParam param) {
        return new SQL() {
            {
                SELECT("count(1) r");
                FROM("fd");

                if (!StringUtils.isEmpty(param.getFd002())) {
                    WHERE("fd002 like concat('%',#{fd002},'%')");
                }

                final Integer duizhao = param.getDuizhao();
                if (duizhao != null) {
                    // 未对照
                    if (duizhao == 0) {
                        WHERE("yyss001 is null");
                    } else if (duizhao == 1) {
                        WHERE("yyss001 is not null");
                    } else {

                    }
                }

            }
        }.toString();
    }

    public String findDownloadByParam(final FdQueryParam param) {
        return new SQL() {
            {
                SELECT("*");
                FROM("fd");
                LEFT_OUTER_JOIN("yyss on yyss.yyss001=fd.yyss001");

                if (!StringUtils.isEmpty(param.getFd002())) {
                    WHERE("fd002 like concat('%',#{fd002},'%')");
                }

                final Integer duizhao = param.getDuizhao();
                if (duizhao != null) {
                    // 未对照
                    if (duizhao == 0) {
                        WHERE("fd.yyss001 is null");
                    } else if (duizhao == 1) {
                        WHERE("fd.yyss001 is not null");
                    } else {

                    }
                }

            }
        }.toString();
    }
}
