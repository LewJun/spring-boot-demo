package com.example.lewjun.domain.one2one_middle;

import com.example.lewjun.domain.BaseObj;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Accessors(chain = true)
@Getter
@Setter
@Entity
public class Wife extends BaseObj {
    @Id
    @GeneratedValue
    private Integer id;

    private String username;

    private int age;

    private float weight;

    private LocalDate birthday;

}
