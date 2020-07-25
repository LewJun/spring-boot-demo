package com.example.lewjun.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@TableName("ab01")
public class Ab01 extends BaseObj {
    /**
     * 部门编号 如果不是id字段，那么需要使用@TableId注释这就是id
     */
    @TableId
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