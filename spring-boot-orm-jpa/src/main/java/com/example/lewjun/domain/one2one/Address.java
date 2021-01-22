package com.example.lewjun.domain.one2one;

import com.example.lewjun.domain.BaseObj;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Accessors(chain = true)
@Data
@Entity
public class Address extends BaseObj {

    @Id
    @GeneratedValue
    private Integer id;

    private String phone;

    private String zipcode;

    private String street;

}
