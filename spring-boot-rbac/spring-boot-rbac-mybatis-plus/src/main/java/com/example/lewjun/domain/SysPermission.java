package com.example.lewjun.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@Accessors(chain = true)
public class SysPermission extends BaseObj {
    private long id;
    private String name;
    private String url;
    private String description;
    private Long parentId;
}
