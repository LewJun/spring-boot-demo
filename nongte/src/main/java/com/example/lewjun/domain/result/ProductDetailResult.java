package com.example.lewjun.domain.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductDetailResult implements Serializable {

    private String title;

    private String html;

    private String html2;

    private String province_name;

    private String city_name;

    private String area_name;
}
