package com.example.lewjun.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
public class Ab01 extends BaseObj {
    /**
     * 部门编号 如果不是id字段，那么需要使用@Id注释这就是id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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