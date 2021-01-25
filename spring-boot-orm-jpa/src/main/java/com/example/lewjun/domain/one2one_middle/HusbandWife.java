package com.example.lewjun.domain.one2one_middle;

import com.example.lewjun.domain.BaseObj;
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
public class HusbandWife extends BaseObj {
    @Id
    @GeneratedValue
    private Integer id;

    private Integer husband;

    private Integer wife;
}
