package com.example.lewjun.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
public class Ac01 extends BaseObj {
    /**
     * 雇员编号
     */
    private int aac001;
    /**
     * 雇员姓名
     */
    @NotBlank(message = "雇员姓名不能为空")
    private String aac002;
    /**
     * 雇员职位
     */
    @NotBlank(message = "雇员职位不能为空")
    private String aac003;
    /**
     * 领导编号
     */
    private Integer aac004;
    /**
     * 雇佣日期 年月日
     */
    private LocalDate aac005;
    /**
     * 性别 -1 未知 0 女 1 男
     */
    private int aac006 = -1;
    /**
     * 身高
     */
    private float aac007;
    /**
     * 体重
     */
    private float aac008;
    /**
     * 兴趣爱好
     */
    private List<String> aac009;
    /**
     * ab01信息
     */
    private Ab01 ab01;
    /**
     * LocalDateTime时间 年月日 是分秒
     */
    private LocalDateTime aac100;
    /**
     * Date时间 Date
     */
    private Date aac101;
}