package com.example.lewjun.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 用户登录信息表
 */
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class SysUserLogin extends BaseObj {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private String password;
}
