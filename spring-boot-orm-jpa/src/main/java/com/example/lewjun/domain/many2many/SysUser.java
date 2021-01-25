package com.example.lewjun.domain.many2many;

import com.example.lewjun.domain.BaseObj;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@Entity
public class SysUser extends BaseObj {
    @Id
    @GeneratedValue
    private Integer id;

    private String uname;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "sys_user_sys_role", joinColumns = @JoinColumn(name = "sys_user_id"), inverseJoinColumns = @JoinColumn(name = "sys_role_id"))
    private List<SysRole> sysRoles;
}
