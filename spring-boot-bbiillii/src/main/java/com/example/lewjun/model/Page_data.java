package com.example.lewjun.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class Page_data {
    private String part;
    private int page;
}
