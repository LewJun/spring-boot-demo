package com.example.lewjun.mapper;

import com.example.lewjun.domain.Ab01;
import org.apache.ibatis.jdbc.SQL;

public class Ab01MapperProvider {
    public String queryByAab001UseProvider(final int aab001) {
        return new SQL() {
            {
//                SELECT("aab001, aab002, aab003");
                SELECT("aab001");
                SELECT("aab002");
                SELECT("aab003");
                FROM("ab01");
                if (aab001 > 0) {
                    WHERE("aab001=#{aab001}");
                } else {
                    WHERE("1=2");
                }
            }
        }.toString();
    }

    public String insertUseProvider(final Ab01 ab01) {
        return new SQL() {
            {
                INSERT_INTO("ab01");
                if (ab01.getAab002() != null) {
                    VALUES("aab002", ab01.getAab002());
                }

                if (ab01.getAab003() != null) {
                    VALUES("aab003", ab01.getAab003());
                }
            }
        }.toString();
    }

    public String updateUseProvider(final Ab01 ab01) {
        return new SQL() {
            {
                UPDATE("ab01");
                SET("aab002 = #{aab002}");
                SET("aab003 = #{aab003}");
                WHERE("aab001 = #{aab001}");
            }
        }.toString();
    }

    public String updateSelectiveProvider(final Ab01 ab01) {
        return new SQL() {
            {
                UPDATE("ab01");
                if (ab01.getAab002() != null) {
                    SET("aab002=#{aab002}");
                }

                if (ab01.getAab003() != null) {
                    SET("aab003=#{aab003}");
                }

                WHERE("aab001=#{aab001}");
            }
        }.toString();
    }

    public String deleteUseProvider(final int aab001) {
        return new SQL() {
            {
                DELETE_FROM("ab01");
                WHERE("aab001=#{aab001}");
            }
        }.toString();
    }

    public String queryByAb01Ac01UseProvider() {
        return new SQL() {
            {
                SELECT("ab01.*");
                SELECT("ac01.*");
                FROM("ab01 as ab01");
                LEFT_OUTER_JOIN("ac01 as ac01 on ac01.aac006 = ab01.aab001");
                ORDER_BY("ab01.aab001 desc");
            }
        }.toString();
    }
}
