package com.example.lewjun.domain.one2many;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;


@Accessors(chain = true)
@Getter
@Setter
@Entity
public class FatherChild implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    private Integer father;

    private Integer child;
}
