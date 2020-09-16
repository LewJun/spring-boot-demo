package com.example.lewjun.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@Accessors(chain = true)
public class SysDeptRole extends BaseObj {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer deptId;
    private Integer roleId;
}
