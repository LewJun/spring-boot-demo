package com.example.lewjun.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Builder
@Accessors(chain = true)
@Data
@TableName("ac01")
public class Ac01 extends BaseObj {
    @TableId("aac001")
    private int aac001;

    private String aac002;

    private String aac003;

    private int aac004;

    private Date aac005;

    private int aac006;
}
