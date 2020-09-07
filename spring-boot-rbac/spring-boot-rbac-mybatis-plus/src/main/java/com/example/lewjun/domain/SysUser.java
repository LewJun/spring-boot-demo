package com.example.lewjun.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 用户基本信息表
 */
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class SysUser extends BaseObj {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String nickname;
    private String email;
    private String avatar;
    private Integer deptId;
}
