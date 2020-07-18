package com.example.lewjun.domain;

import com.example.lewjun.jsr.group.UpdateGroup;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Accessors(chain = true)
public class Ab01 extends BaseObj {
    /**
     * 部门编号
     * <pre>
     *     WARNING: 当group为UpdateGroup时，@NotNull校验生效
     * </pre>
     */
    @NotNull(message = "部门编号不能为空", groups = {UpdateGroup.class})
    private Integer aab001;
    /**
     * 部门名称
     */
    @Size(min = 3, max = 32, message = "长度介于[3, 32]")
    @NotBlank(message = "部门名称不能为空")
    private String aab002;
    /**
     * 部门所在位置
     */
    @Size(min = 3, max = 32, message = "长度介于[3, 32]")
    @NotBlank(message = "部门所在位置不能为空")
    private String aab003;
}