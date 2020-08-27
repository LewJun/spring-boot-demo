package com.example.lewjun.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@ToString(callSuper = true)
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class SysDeptNode extends SysDept {
    private List<SysDeptNode> children;
}
