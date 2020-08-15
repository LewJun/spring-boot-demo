package com.example.lewjun.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Data
public class Ac01 extends BaseObj {
    private Integer aac001;

    private String aac002;

    private String aac003;

    private Integer aac004;

    private LocalDate aac005;

    private Integer aac006;
}
