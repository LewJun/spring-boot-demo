package com.example.lewjun.mapper;

import com.example.lewjun.domain.vo.ProductQueryParamVO;
import org.apache.ibatis.jdbc.SQL;

public class ProductMapperProvider {
    public String queryByConditions(ProductQueryParamVO vo) {
        return new SQL() {
            {
                SELECT("t.id, t.title, t.desc, t.level, t.status, t.create_time, " +
                        "concat(t.province_name, ' ', t.city_name, ' ', t.area_name) as region");
                FROM("product t");
                if (vo.getTitle() != null && vo.getTitle().trim().length() != 0) {
                    WHERE("t.title like concat('%',#{title},'%')");
                }
                if (vo.getDesc() != null && vo.getDesc().trim().length() != 0) {
                    WHERE("t.desc like concat('%',#{desc},'%')");
                }
                if (vo.getStatus() != null) {
                    WHERE("t.status=#{status}");
                }
                if (vo.getLevel() != null) {
                    WHERE("t.level=#{level}");
                }
                if (vo.getProvince_code() != null) {
                    WHERE("t.province_code=#{province_code}");
                }
                if (vo.getCity_code() != null) {
                    WHERE("t.city_code=#{city_code}");
                }
                if (vo.getArea_code() != null) {
                    WHERE("t.area_code=#{area_code}");
                }
                LIMIT("#{pageNumber}");
                OFFSET("#{offset}");
            }
        }.toString();
    }

    public String queryCountByConditions(ProductQueryParamVO vo) {
        return new SQL() {
            {
                SELECT("count(id) as total");
                FROM("product t");
                if (vo.getTitle() != null && vo.getTitle().trim().length() != 0) {
                    WHERE("t.title like concat('%',#{title},'%')");
                }
                if (vo.getDesc() != null && vo.getDesc().trim().length() != 0) {
                    WHERE("t.desc like concat('%',#{desc},'%')");
                }
                if (vo.getStatus() != null) {
                    WHERE("t.status=#{status}");
                }
                if (vo.getLevel() != null) {
                    WHERE("t.level=#{level}");
                }
                if (vo.getProvince_code() != null) {
                    WHERE("t.province_code=#{province_code}");
                }
                if (vo.getCity_code() != null) {
                    WHERE("t.city_code=#{city_code}");
                }
                if (vo.getArea_code() != null) {
                    WHERE("t.area_code=#{area_code}");
                }
                LIMIT(1);
            }
        }.toString();
    }
}
