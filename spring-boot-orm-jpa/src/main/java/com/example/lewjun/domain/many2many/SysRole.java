package com.example.lewjun.domain.many2many;

import com.example.lewjun.domain.BaseObj;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Data
@Accessors(chain = true)
@Entity
public class SysRole extends BaseObj {
    @Id
    @GeneratedValue
    private Integer id;

    private String uname;

    @ManyToMany
    private List<SysUser> sysUsers;
}
