package com.example.lewjun.mapper;

import com.example.lewjun.domain.Product;
import com.example.lewjun.domain.result.ProductDetailResult;
import com.example.lewjun.domain.result.ProductListQueryResult;
import com.example.lewjun.domain.result.ProductSearchResult;
import com.example.lewjun.domain.vo.ProductQueryParamVO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@CacheNamespace(flushInterval = 60000)
@Repository
public interface ProductMapper {

    @SelectProvider(type = ProductMapperProvider.class, method = "queryByRegionCode")
    List<ProductSearchResult> queryByRegionCode(
            @Param("province_code") Integer province_code,
            @Param("city_code") Integer city_code,
            @Param("area_code") Integer area_code);

    @SelectProvider(type = ProductMapperProvider.class, method = "queryByConditions")
    List<ProductListQueryResult> queryByConditions(ProductQueryParamVO vo);

    @SelectProvider(type = ProductMapperProvider.class, method = "queryCountByConditions")
    int queryCountByConditions(ProductQueryParamVO vo);

    @Select("select p.title, p.html from product p where p.id=#{id} limit 1")
    ProductDetailResult queryDetailById(Integer id);

    @Update("update product set status=#{status}, update_time=now() where id=#{id}")
    int updateStatus(Integer id, Integer status);

    @Select("select p.id, p.title, p.desc, p.province_code, p.city_code, p.area_code, p.html, p.pic_url, p.db, p.keywords, " +
            "p.show_prov, p.show_city, p.show_area, " +
            "p.level_prov, p.level_city, p.level_area " +
            "from product p where p.id=#{id} limit 1")
    Product queryEditById(Integer id);

    @Insert("insert into product(title, `desc`, html, " +
            "level_prov, level_city, level_area, " +
            "show_prov, show_city, show_area, " +
            "db, keywords, pic_url, " +
            "province_code, province_name, " +
            "city_code, city_name, " +
            "area_code, area_name, " +
            "create_time) " +
            "values(#{title}, #{desc}, #{html}, " +
            "#{level_prov}, #{level_city}, #{level_area}, " +
            "#{show_prov}, #{show_city}, #{show_area}, " +
            "#{db}, #{keywords}, #{pic_url}, " +
            "#{province_code}, #{province_name}, " +
            "#{city_code}, #{city_name}, " +
            "#{area_code}, #{area_name}, " +
            "now())")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insert(Product product);

    @Update("update product set title=#{title}, `desc`=#{desc}, html=#{html}, " +
            "pic_url=#{pic_url}, db=#{db}, keywords=#{keywords}," +
            "level_prov=#{level_prov}, level_city=#{level_city}, level_area=#{level_area}, " +
            "show_prov=#{show_prov}, show_city=#{show_city}, show_area=#{show_area}, " +
            "province_code=#{province_code}, province_name=#{province_name}, " +
            "city_code=#{city_code}, city_name=#{city_name}, area_code=#{area_code}, area_name=#{area_name}, " +
            "update_time=now() " +
            "where id=#{id}")
    int update(Product product);

    @Update("update product set status=0, update_time=now() where id=#{id}")
    int delete(Integer id);

    @SelectProvider(type = ProductMapperProvider.class, method = "queryByKeywords")
    List<ProductSearchResult> queryByKeywords(@Param("keywords") String keywords,
                                              @Param("pageNumber") Integer pageNumber,
                                              @Param("offset") Integer offset);
}
