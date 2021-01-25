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
public class SysRole extends BaseObj {
    @Id
    @GeneratedValue
    private Integer id;

    private String uname;

    @ManyToMany(mappedBy = "sysRoles", fetch = FetchType.LAZY)
    private List<SysUser> sysUsers;
}
