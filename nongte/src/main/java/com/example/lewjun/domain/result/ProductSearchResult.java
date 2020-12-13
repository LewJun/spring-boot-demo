package com.example.lewjun.domain.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductSearchResult implements Serializable {
    private Integer id;

    private String title;

    private String desc;

    private String pic_url;

    private Integer db;
}
