package com.example.lewjun.mapper;

import com.example.lewjun.domain.Region;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@CacheNamespace(flushInterval = 600000)
@Repository
public interface RegionMapper {

    @Select("select t.city_code as code, t.city_name as name from region_city t where t.province_code=#{province_code}")
    List<Region> queryCitiesByProvinceCode(Integer province_code);

    @Select("select t.area_code as code, t.area_name as name from region_area t where t.city_code=#{city_code}")
    List<Region> queryAreasByCityCode(Integer city_code);

    @Select("select t.province_code code, t.province_name name from region_province t")
    List<Region> queryAllProvs();
}
