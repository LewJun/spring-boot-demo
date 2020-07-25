package com.example.lewjun.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Data
@TableName("ac01")
public class Ac01 extends BaseObj {
    @TableId("aac001")
    private Integer aac001;

    private String aac002;

    private String aac003;

    private Integer aac004;

    private Date aac005;

    private Integer aac006;
}
