package com.example.lewjun.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class Ac01Ab01 {
    private int aac001;

    private String aac002;

    private String aac003;

    private int aac004;

    private Date aac005;

    private Ab01 ab01;
}
