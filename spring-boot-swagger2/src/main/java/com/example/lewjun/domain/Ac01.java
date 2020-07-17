package com.example.lewjun.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@ApiModel("Ac01基本信息")
@Data
@Accessors(chain = true)
public class Ac01 extends BaseObj {
    @ApiModelProperty("主键")
    private int aac001;
    @ApiModelProperty("雇员名称")
    private String aac002;
    private String aac003;
    private Integer aac004;
    private LocalDate aac005;
    private int aac006;
    private float aac007;
    private float aac008;
    @ApiModelProperty("爱好")
    private List<String> aac009;
    @ApiModelProperty("Ab01引用信息")
    private Ab01 ab01;
    private LocalDateTime aac100;
    private Date aac101;
}