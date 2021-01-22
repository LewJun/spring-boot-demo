package com.example.lewjun.domain.one2many;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Accessors(chain = true)
@Data
@Entity
public class Child {

    @Id
    @GeneratedValue
    private Integer id;

    private String uname;
}
