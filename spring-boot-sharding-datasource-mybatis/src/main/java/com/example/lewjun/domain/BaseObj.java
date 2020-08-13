package com.example.lewjun.domain;



import lombok.Data;

import java.io.Serializable;

@Data
// 若为null，则不要返回给前端
public class BaseObj implements Serializable {
    /**
     * 部门编号 如果不是id字段，那么需要使用@TableId注释这就是id
     */
    protected Long id;
}
