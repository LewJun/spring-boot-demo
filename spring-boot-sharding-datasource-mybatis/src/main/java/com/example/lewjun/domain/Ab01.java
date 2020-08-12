package com.example.lewjun.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class Ab01 extends BaseObj {
    /**
     * 部门编号 如果不是id字段，那么需要使用@TableId注释这就是id
     */
    private Long id;
    /**
     * 部门名称
     */
    private String aab002;
    /**
     * 部门所在位置
     */
    private String aab003;

    private Integer aab001;
}