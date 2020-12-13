package com.example.lewjun.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductQueryParamVO extends BaseQueryParam implements Serializable {
    private String title;

    private String desc;

    private Integer level_prov;
    private Integer level_city;
    private Integer level_area;

    private Integer show_prov;
    private Integer show_city;
    private Integer show_area;

    private Integer db;

    private String keywords;

    private Integer status;

    private Integer province_code;

    private Integer city_code;

    private Integer area_code;
}
