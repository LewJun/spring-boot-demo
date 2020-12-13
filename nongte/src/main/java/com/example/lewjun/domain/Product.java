package com.example.lewjun.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class Product implements Serializable {
    private Integer id;

    private String title;

    private String pic_url;

    private String desc;

    private Integer level_prov = 999;

    private Integer level_city = 999;

    private Integer level_area = 999;

    private Integer show_prov = 0;

    private Integer show_city = 0;

    private Integer show_area = 0;

    private String keywords;

    private String html;

    private Integer province_code;

    private String province_name;

    private Integer city_code;

    private String city_name;

    private Integer area_code;

    private String area_name;

    private String create_time;

    private String update_time;

    private Integer db;
}
