package com.example.lewjun.domain.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductListQueryResult implements Serializable {
    private Integer id;

    private String title;

    private String desc;

    private String keywords;

    private Integer db;

    private Integer level_prov;
    private Integer level_city;
    private Integer level_area;


    private Integer show_prov;
    private Integer show_city;
    private Integer show_area;

    private Integer status;

    private String create_time;

    private String region;
}
