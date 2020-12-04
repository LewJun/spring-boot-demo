package com.example.lewjun.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductQueryParamVO extends BaseQueryParam implements Serializable {
    private String title;

    private String desc;

    private Integer level;

    private Integer status;

    private Integer province_code;

    private Integer city_code;

    private Integer area_code;
}
