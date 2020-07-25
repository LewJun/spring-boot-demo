package com.example.lewjun.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(chain = true)
@Data
public class Ab01Ac01 {
    private List<Ac01> ac01s;
    private Integer aab001;
    /**
     * 部门名称
     */
    private String aab002;
    /**
     * 部门所在位置
     */
    private String aab003;
}
