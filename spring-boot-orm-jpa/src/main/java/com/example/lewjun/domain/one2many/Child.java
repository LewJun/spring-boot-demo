package com.example.lewjun.domain.one2many;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Accessors(chain = true)
@Getter
@Setter
@Entity
public class Child {

    @Id
    @GeneratedValue
    private Integer id;

    private String uname;
}
