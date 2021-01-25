package com.example.lewjun.domain.one2one;

import com.example.lewjun.domain.BaseObj;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;

@Accessors(chain = true)
@Getter
@Setter
@Entity
public class People extends BaseObj {
    @Id
    @GeneratedValue
    private Integer id;

    private String username;

    private int age;

    private float weight;

    private LocalDate birthday;

    // People是关系的维护端，当删除People时，级联删除 address
    @OneToOne(cascade = CascadeType.ALL)
    // People中的address_id，与Address中的id关联，通常是关联表的主键，则下面的@JoinColumn可以省略，
    // 但是如果要关联其它的字段，则必须添加@JoinColumn
//    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
}
