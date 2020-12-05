package com.example.lewjun.domain.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductByRegionCodeResult implements Serializable {
    private Integer id;

    private String title;

    private String pic_url;
}
