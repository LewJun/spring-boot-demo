package com.example.lewjun.domain.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductDetailResult implements Serializable {

    private String title;

    private String html;

    private String html2;

    private Integer province_code;

    private String province_name;

    private Integer city_code;

    private String city_name;

    private Integer area_code;

    private String area_name;
}
