package com.example.lewjun.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
// 联合主键
@IdClass(SysUserRole.class)
public class SysUserRole extends BaseObj {
    @Id
    private long userId;
    @Id
    private long roleId;
}
