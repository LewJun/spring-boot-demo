package com.example.lewjun.domain;

import com.example.lewjun.jsr.custom.Hobby;
import com.example.lewjun.jsr.custom.Mobile;
import com.example.lewjun.jsr.custom.Sex;
import com.example.lewjun.jsr.group.UpdateGroup;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
public class Ac01 extends BaseObj {
    /**
     * 雇员编号
     * <pre>
     *     WARNING: 当group为UpdateGroup时，@NotNull校验生效
     * </pre>
     */
    @NotNull(message = "雇员编号不能为空", groups = {UpdateGroup.class})
    private Integer aac001;
    /**
     * 雇员姓名
     */
    @Size(min = 3, max = 32, message = "长度介于[3, 32]")
    @NotBlank(message = "雇员姓名不能为空")
    private String aac002;
    /**
     * 雇员职位
     */
    @Size(min = 3, max = 32, message = "长度范围[3, 32]")
    @NotBlank(message = "雇员职位不能为空")
    private String aac003;
    /**
     * 领导编号
     */
    private Integer aac004;
    /**
     * 雇佣日期 年月日
     */
    private LocalDate aac005;
    /**
     * 性别 -1 未知 0 女 1 男
     */
    @Sex
    private Integer aac006;
    /**
     * 身高
     */
    @DecimalMin(value = "1.0", message = "身高必须>=1米")
    @DecimalMax(value = "2.5", message = "身高必须<=2.5米")
    @Digits(integer = 1, fraction = 2, message = "小数点后最多保留2位小数")
    private float aac007;
    /**
     * 体重
     */
    @DecimalMin(value = "30", message = "体重必须>=30KG")
    @DecimalMax(value = "100", message = "体重必须<=100KG")
    @Digits(integer = 3, fraction = 2, message = "小数点后最多保留2位小数")
    private float aac008;
    /**
     * 兴趣爱好
     */
    @Hobby
    private List<Integer> aac009;
    /**
     * ab01信息
     */
    private Ab01 ab01;
    /**
     * 下一次生日时间 年月日 是分秒
     */
    @Future(message = "下一次生日时间必须是未来")
    private LocalDateTime aac100;
    /**
     * 出生日期 Date
     */
    @Past(message = "出生日期必须是过去的日期")
    private Date aac101;

    /**
     * 电子邮箱
     */
    @Email(message = "邮箱格式错误")
    private String aac102;

    /**
     * 年龄
     */
    @Range(min = 18, max = 150, message = "年龄范围[18, 150]")
    @NotNull(message = "年龄不能为空")
    private Integer aac103;

    /**
     * 邮政编码
     */
    @Pattern(regexp = "[0-9]{6}", message = "邮政编码长度或格式不对")
    private String aac104;

    /**
     * 电话号码
     */
    @Mobile
    private String aac105;
}