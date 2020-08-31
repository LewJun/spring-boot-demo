package com.example.lewjun.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@Accessors(chain = true)
public class SysPermission extends BaseObj {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String url;
    private String description;
    private Long parentId;
}
