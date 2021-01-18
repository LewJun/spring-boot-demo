package com.example.lewjun.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class DemoData {

    @ExcelProperty("id")
    private Integer id;

    @ExcelProperty(value = "STR", index = 1)
    private String string;

    private Date date;

    private Double doubleData;
}
