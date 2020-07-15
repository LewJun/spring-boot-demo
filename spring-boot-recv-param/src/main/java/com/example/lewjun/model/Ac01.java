package com.example.lewjun.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
public class Ac01 extends BaseObj {
    private int aac001;
    private String aac002;
    private String aac003;
    private Integer aac004;
    private LocalDate aac005;
    private int aac006;
    private float aac007;
    private float aac008;
    private List<String> aac009;
    private Ab01 ab01;
    private LocalDateTime aac100;
    private Date aac101;
}