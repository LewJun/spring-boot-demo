package com.example.lewjun.domain.one2many2;

import com.example.lewjun.domain.BaseObj;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Accessors(chain = true)
@Getter
@Setter
@Entity
public class Mather extends BaseObj {

    @Id
    @GeneratedValue
    private Integer id;

    private String uname;

    @OneToMany(
            mappedBy = "mather"
            // 删除Father，会级联删除child
            , cascade = CascadeType.ALL
            // 懒加载
            , fetch = FetchType.LAZY
    )
    private List<Son> sons;
}
