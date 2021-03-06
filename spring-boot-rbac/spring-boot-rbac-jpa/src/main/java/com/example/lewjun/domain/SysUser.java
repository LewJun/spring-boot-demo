package com.example.lewjun.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
public class SysUser extends BaseObj {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 32, nullable = false, unique = true)
    private String username;
    @Column(length = 32, nullable = false)
    private String password;
}
