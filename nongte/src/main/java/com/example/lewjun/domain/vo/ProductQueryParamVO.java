package com.example.lewjun.domain.vo;

import lombok.Data;

@Data
public class ProductQueryParamVO {
    private String title;

    private String desc;

    private Integer level;

    private Integer status;

    private Integer province_code;

    private Integer city_code;

    private Integer area_code;
}
