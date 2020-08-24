package com.example.lewjun.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
// 联合主键
@IdClass(SysRolePermission.class)
public class SysRolePermission extends BaseObj {
    @Id
    private long roleId;
    @Id
    private long permissionId;
}
