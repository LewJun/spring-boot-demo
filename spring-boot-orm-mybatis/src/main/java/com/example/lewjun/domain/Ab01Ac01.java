package com.example.lewjun.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Accessors(chain = true)
@Data
public class Ab01Ac01 extends Ab01 implements Serializable {
    private List<Ac01> ac01s;
}
