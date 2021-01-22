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
            // 删除Father，会级联删除child
            cascade = CascadeType.ALL,
            // 懒加载
            fetch = FetchType.LAZY)
    private List<Child> children;
}
