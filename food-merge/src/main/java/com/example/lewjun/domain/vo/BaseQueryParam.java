package com.example.lewjun.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseQueryParam implements Serializable {
     private Integer pageNumber;
     private Integer offset;
}
