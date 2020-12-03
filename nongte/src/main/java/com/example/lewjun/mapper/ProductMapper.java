package com.example.lewjun.mapper;

import com.example.lewjun.domain.Product;
import com.example.lewjun.domain.result.ProductDetailResult;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@CacheNamespace(flushInterval = 60000)
@Repository
public interface ProductMapper {

    @Select("select p.id, p.title, p.desc, p.pic_url as picUrl, " +
            "concat(p.province_name, ' ', p.city_name, ' ', p.area_name) as region " +
            "from product p where p.status=1 and p.province_code=#{provinceCode}")
    List<Product> queryByProvinceCode(Integer provinceCode);

    @Select("select p.id, p.title, p.desc, p.pic_url as picUrl, " +
            "concat(p.province_name, ' ', p.city_name, ' ', p.area_name) as region " +
            "from product p where p.status=1 and p.city_code=#{cityCode}")
    List<Product> queryByCityCode(Integer cityCode);

    @Select("select p.id, p.title, p.desc, p.pic_url as picUrl, " +
            "concat(p.province_name, ' ', p.city_name, ' ', p.area_name) as region " +
            "from product p where p.status=1 and p.area_code=#{areaCode}")
    List<Product> queryByAreaCode(Integer areaCode);

//    List<Product> queryByConditions(ProductQueryParamVO vo);

    @Select("select p.title, p.html from product p where p.id=#{id} limit 1")
    ProductDetailResult queryDetailById(Integer id);

    @Update("update product set status=#{status} where id=#{id}")
    int updateStatus(Integer id, Integer status);
}
