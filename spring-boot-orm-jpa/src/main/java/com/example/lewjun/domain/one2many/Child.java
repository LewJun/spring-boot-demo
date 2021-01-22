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

//    没有必要再一对多的多的这一方使用@ManyToOne，JPA生成了一个中间表father_children，里面自有维护
//    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH},
//            // optional=false表示father不能为空，JPA生成了一个中间表father_children，里面自有维护
//            optional = false
//    )
//    private Father father;
}
