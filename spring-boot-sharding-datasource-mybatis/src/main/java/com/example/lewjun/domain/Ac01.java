package com.example.lewjun.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Data
public class Ac01 extends BaseObj {
    private String aac002;

    private String aac003;

    private Integer aac004;

    private Date aac005;

    private Long aac006;
}
