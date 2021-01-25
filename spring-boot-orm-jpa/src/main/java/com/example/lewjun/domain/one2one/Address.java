package com.example.lewjun.domain.one2one;

import com.example.lewjun.domain.BaseObj;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Address extends BaseObj {

    @Id
    @GeneratedValue
    private Integer id;

    private String phone;

    private String zipcode;

    private String street;

    // 注意，关系被维护表中的mappedBy必须写，不然会出现获取对象为空的情况
    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
    private People people;

}
