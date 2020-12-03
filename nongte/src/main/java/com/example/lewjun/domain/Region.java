package com.example.lewjun.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class Region implements Serializable {
    private Integer code;
    private String name;
}
