package com.example.lewjun.domain.one2many2;

import com.example.lewjun.domain.BaseObj;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Accessors(chain = true)
@Getter
@Setter
@Entity
public class Son extends BaseObj {

    @Id
    @GeneratedValue
    private Integer id;

    private String uname;

    @ManyToOne(
            cascade = {CascadeType.MERGE, CascadeType.REFRESH}
//            // optional=false表示father不能为空
            , optional = false
            , fetch = FetchType.LAZY
    )
    private Mather mather;
}
