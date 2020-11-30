package com.example.lewjun.domain;

import lombok.Data;

@Data
public class Product {
    private Integer id;

    private String title;

    private String pic;

    private String description;

    private String html;

    public Product(final Integer id, final String title, final String pic) {
        this.id = id;
        this.title = title;
        this.pic = pic;
    }
}
