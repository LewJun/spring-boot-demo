package com.example.lewjun.model;

import com.example.lewjun.util.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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

    @JsonSerialize(using = LocalDateToLongJsonSerializer.class)
    @JsonDeserialize(using = LongToLocalDateJsonDeserializer.class)
    private LocalDate aac005;

    private int aac006;
    private float aac007;
    private float aac008;
    private List<String> aac009;
    private Ab01 ab01;

    @JsonSerialize(using = LocalDateTimeToLongJsonSerializer.class)
    @JsonDeserialize(using = LongToLocalDateTimeJsonDeserializer.class)
    private LocalDateTime aac100;

    @JsonSerialize(using = DateToLongJsonSerializer.class)
    @JsonDeserialize(using = LongToDateJsonDeserializer.class)
    private Date aac101;
}