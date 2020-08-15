package com.example.lewjun.domain;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@ToString(callSuper = true)
@Data
@Accessors(chain = true)
public class Ac01Ab01 extends Ac01 {
    private Ab01 ab01;
}
