package com.example.lewjun.domain;

import lombok.Data;

@Data
public class Product {
    private Integer id;

    private String title;

    private String pic;

    private String desc;

    private Integer level;

    private String html;

    private String province;

    private String city;

    private String area;

    private String create_time;

    private String update_time;

    public Product(final Integer id, final String title, final String pic) {
        this.id = id;
        this.title = title;
        this.pic = pic;
    }
}
