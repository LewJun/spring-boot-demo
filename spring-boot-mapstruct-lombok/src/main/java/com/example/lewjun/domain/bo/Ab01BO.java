package com.example.lewjun.domain.bo;

import lombok.Data;
import lombok.experimental.Accessors;
import java.util.Date;
import java.util.List;

@Accessors(chain = true)
@Data
public class Ab01BO extends BaseObj {
    /**
     * 部门编号 如果不是id字段，那么需要使用@TableId注释这就是id
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

    private Date bob;

    private List<String> hobbies;
}