package com.example.lewjun.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel("Ab01基本信息")
@Data
@Accessors(chain = true)
public class Ab01 extends BaseObj {
    @ApiModelProperty("主键")
    private int aab001;
    @ApiModelProperty("部门名称")
    private String aab002;
    @ApiModelProperty("部门所在地")
    private String aab003;
}