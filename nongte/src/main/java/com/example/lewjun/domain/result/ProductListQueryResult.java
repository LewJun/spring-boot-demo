package com.example.lewjun.domain.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductListQueryResult implements Serializable {
    private Integer id;

    private String title;

    private String desc;

    private Integer level;

    private Integer status;

    private String create_time;

    private String region;
}
