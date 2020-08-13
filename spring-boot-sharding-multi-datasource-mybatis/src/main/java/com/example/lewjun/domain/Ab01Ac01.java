package com.example.lewjun.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Ab01Ac01 implements Serializable {

    /**
     * 部门名称
     */
    private String aab002;
    /**
     * 部门所在位置
     */
    private String aab003;

    private Integer aab001;

    List<Ac01> ac01s;
}
