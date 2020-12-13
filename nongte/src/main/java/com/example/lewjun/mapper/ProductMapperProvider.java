package com.example.lewjun.mapper;

import com.example.lewjun.domain.vo.ProductQueryParamVO;
import org.apache.ibatis.jdbc.SQL;

public class ProductMapperProvider {

    public String queryByRegionCode(
            final Integer province_code,
            final Integer city_code,
            final Integer area_code) {
        return new SQL() {
            {
                String orderSql = "";
                SELECT("p.id, p.title, p.desc, p.pic_url, p.db");
                FROM("product p");
                WHERE("status=1");
                if (province_code != null) {
                    WHERE("province_code=#{province_code}");
                    WHERE("show_prov=1");
                    orderSql = "level_prov asc";
                } else if (city_code != null) {
                    WHERE("city_code=#{city_code}");
                    WHERE("show_city=1");
                    orderSql = "level_city asc";
                } else if (area_code != null) {
                    WHERE("area_code=#{area_code}");
                    WHERE("show_area=1");
                    orderSql = "level_area asc";
                } else {
                }
                ORDER_BY("id desc");
                if (orderSql != null && orderSql.length() > 0) {
                    ORDER_BY(orderSql);
                }
            }
        }.toString();
    }

    public String queryByConditions(final ProductQueryParamVO vo) {
        return new SQL() {
            {
                SELECT("t.id, t.title, t.desc, t.keywords, t.db, " +
                        "t.show_prov, t.show_city, t.show_area, " +
                        "t.level_prov, t.level_city, t.level_area, t.status, " +
                        "DATE_FORMAT(t.create_time, '%Y-%m-%d') as create_time, " +
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
                if (vo.getLevel_prov() != null) {
                    WHERE("t.level_prov=#{level_prov}");
                }
                if (vo.getLevel_city() != null) {
                    WHERE("t.level_city=#{level_city}");
                }
                if (vo.getLevel_area() != null) {
                    WHERE("t.level_area=#{level_area}");
                }

                if (vo.getShow_prov() != null) {
                    WHERE("t.show_prov=#{show_prov}");
                }
                if (vo.getShow_city() != null) {
                    WHERE("t.show_city=#{show_city}");
                }
                if (vo.getShow_area() != null) {
                    WHERE("t.show_area=#{show_area}");
                }

                if (vo.getKeywords() != null) {
                    WHERE("t.keywords like concat('%', #{keywords}, '%')");
                }

                if (vo.getDb() != null) {
                    WHERE("t.db=#{db}");
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
                WHERE("status!=0");
                ORDER_BY("level_prov asc");
                ORDER_BY("level_city asc");
                ORDER_BY("level_area asc");
                ORDER_BY("id desc");
                LIMIT("#{pageNumber}");
                OFFSET("#{offset}");
            }
        }.toString();
    }

    public String queryCountByConditions(final ProductQueryParamVO vo) {
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

                if (vo.getLevel_prov() != null) {
                    WHERE("t.level_prov=#{level_prov}");
                }
                if (vo.getLevel_city() != null) {
                    WHERE("t.level_city=#{level_city}");
                }
                if (vo.getLevel_area() != null) {
                    WHERE("t.level_area=#{level_area}");
                }

                if (vo.getShow_prov() != null) {
                    WHERE("t.show_prov=#{show_prov}");
                }
                if (vo.getShow_city() != null) {
                    WHERE("t.show_city=#{show_city}");
                }
                if (vo.getShow_area() != null) {
                    WHERE("t.show_area=#{show_area}");
                }

                if (vo.getKeywords() != null) {
                    WHERE("t.keywords like concat('%', #{keywords}, '%')");
                }

                if (vo.getDb() != null) {
                    WHERE("t.db=#{db}");
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
                WHERE("status!=0");
                LIMIT(1);
            }
        }.toString();
    }

    public String queryByKeywords(final String keywords, final Integer pageNumber, final Integer offset) {
        String likeSql = "";
        if (keywords != null && keywords.trim().length() > 0) {
            likeSql = "like concat('%', '" + keywords + "','%')";
        }
        final String finalLikeSql = likeSql;
        return new SQL() {
            {
                SELECT("t.id, t.title, t.desc, t.pic_url, t.db");
                FROM("product t");
                if (finalLikeSql.length() > 0) {
                    OR().WHERE("t.title " + finalLikeSql);
                    OR().WHERE("t.`desc` " + finalLikeSql);
                    OR().WHERE("t.province_name " + finalLikeSql);
                    OR().WHERE("t.city_name " + finalLikeSql);
                    OR().WHERE("t.area_name " + finalLikeSql);
                    OR().WHERE("t.keywords" + finalLikeSql);
                }
                AND().WHERE("t.status=1");
                ORDER_BY("level_prov asc");
                ORDER_BY("level_city asc");
                ORDER_BY("level_area asc");
                ORDER_BY("id desc");
                LIMIT(pageNumber);
                OFFSET(offset);
            }
        }.toString();
    }
}
