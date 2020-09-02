package com.example.lewjun.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class Entry {
    private String title;
    private String type_tag;
    private Page_data page_data;
    private Ep ep;
}
