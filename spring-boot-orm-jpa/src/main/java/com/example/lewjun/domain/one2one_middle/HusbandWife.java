package com.example.lewjun.domain.one2one_middle;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Accessors(chain = true)
@Data
@Entity
public class HusbandWife {
    @Id
    @GeneratedValue
    private Integer id;

    private Integer husband;

    private Integer wife;
}
