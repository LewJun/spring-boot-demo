package com.example.lewjun.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class Ab01 extends BaseObj {
    /**
     * 部门编号
     */
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