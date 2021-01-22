package com.example.lewjun.domain.one2many;

import com.example.lewjun.domain.BaseObj;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Accessors(chain = true)
@Data
@Entity
public class Father extends BaseObj {

    @Id
    @GeneratedValue
    private Integer id;

    private String uname;

    @OneToMany(
            // 没有必要再一对多的多的这一方使用@ManyToOne，JPA生成了一个中间表father_children，里面自有维护，所以mappedBy也就没必要了。
            // mappedBy = "father",
            // 删除Father，会级联删除child
            cascade = CascadeType.ALL,
            // 懒加载
            fetch = FetchType.LAZY)
    private List<Child> children;
}
