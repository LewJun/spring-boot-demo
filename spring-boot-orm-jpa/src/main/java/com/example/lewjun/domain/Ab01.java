package com.example.lewjun.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Accessors(chain = true)
@Entity
public class Ab01 extends BaseObj {
    /**
     * 部门编号 如果不是id字段，那么需要使用@Id注释这就是id
     */
    @Id
    @GeneratedValue
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