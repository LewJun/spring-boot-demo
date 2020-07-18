package com.example.lewjun.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class Ab01 extends BaseObj {
    /**
     * 部门编号
     */
    private int aab001;
    /**
     * 部门名称
     */
    private String aab002;
    /**
     * 部门所在位置
     */
    @NotNull
    private String aab003;
}