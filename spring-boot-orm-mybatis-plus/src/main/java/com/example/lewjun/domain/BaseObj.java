package com.example.lewjun.domain;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

// 若为null，则不要返回给前端
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class BaseObj implements Serializable {

    /**
     * createTime 在新增的时候自动填充
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * updateTime 在新增修改的时候自动填充
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
