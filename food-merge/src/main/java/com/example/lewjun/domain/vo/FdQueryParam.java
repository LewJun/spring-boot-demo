package com.example.lewjun.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class FdQueryParam extends BaseQueryParam implements Serializable {
    private String fd002;
    private Integer duizhao;
}
