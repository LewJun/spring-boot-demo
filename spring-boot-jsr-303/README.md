# spring-boot-jsr-303

> JSR-303实现请求参数校验

[TOC]

## 添加依赖
```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
```

## 在要校验的字段上添加约束

```java
    @NotNull
    private String aab003;
```

## 在要校验的参数实体上添加注解@Valid

```java
    @PostMapping
    public Ab01 postAb01(@RequestBody @Valid final Ab01 ab01) {
        ab01Service.save(ab01);
        return ab01;
    }
```

完成以上步骤后，测试保存ab01时，不上传aab003数据，此时就会出现异常：

```json
{
    "timestamp": "2020-07-18T11:43:57.543+00:00",
    "status": 400,
    "error": "Bad Request",
"message": "Validation failed for object='ab01'. Error count: 1",
    "errors": [
        {
            "codes": [
                "NotNull.ab01.aab003",
                "NotNull.aab003",
                "NotNull.java.lang.String",
                "NotNull"
            ],
            "arguments": [
                {
                    "codes": [
                        "ab01.aab003",
                        "aab003"
                    ],
                    "arguments": null,
                    "defaultMessage": "aab003",
                    "code": "aab003"
                }
            ],
            "defaultMessage": "must not be null",
            "objectName": "ab01",
            "field": "aab003",
            "rejectedValue": null,
            "bindingFailure": false,
            "code": "NotNull"
        }
    ],
    "path": "/demo/ab01s"
}
```

## 常用验证注解

* @Null 限制只能为null
* @NotNull 限制必须不为null
* @AssertFalse 限制必须为false
* @AssertTrue 限制必须为true
* @DecimalMax(value) 限制必须为一个不大于指定值的数字
* @DecimalMin(value) 限制必须为一个不小于指定值的数字
* @Digits(integer,fraction) 限制必须为一个小数，且整数部分的位数不能超过integer，小数部分的位数不能超过fraction
* @Future 限制必须是一个将来的日期
* @Max(value) 限制必须为一个不大于指定值的数字
* @Min(value) 限制必须为一个不小于指定值的数字
* @Past 限制必须是一个过去的日期
* @Pattern(value) 限制必须符合指定的正则表达式
* @Size(max,min) 限制字符长度必须在min到max之间
* @Past 验证注解的元素值（日期类型）比当前时间早
* @NotEmpty 验证注解的元素值不为null且不为空（字符串长度不为0、集合大小不为0）
* @NotBlank 验证注解的元素值不为空（不为null、去除首位空格后长度为0），不同于@NotEmpty，@NotBlank只应用于字符串且在比较时会去除字符串的空格
* @Email 验证注解的元素值是Email，也可以通过正则表达式和flag指定自定义的email格式

### 校验使用示例

* [UpdateGroup](src/main/java/com/example/lewjun/jsr/group/UpdateGroup.java)

```java
package com.example.lewjun.jsr.group;

public interface UpdateGroup {
}
```

给校验器指定分组，在做更新的时候，要求必须传递编码，而在新增的时候，编码可以不必要传递。

```java
@NotNull(message = "部门编号不能为空", groups = {UpdateGroup.class})
    private Integer aab001;
public Ab01 putAc01(@RequestBody @Validated(UpdateGroup.class) final Ab01 ab01) 
```

* [Ab01.java](src/main/java/com/example/lewjun/domain/Ab01.java)

```java

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
```

* [Ac01.java](src/main/java/com/example/lewjun/domain/Ac01.java)

```java
package com.example.lewjun.domain;

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
    private int aac006 = -1;
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
    private List<String> aac009;
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
    @Pattern(regexp = "[0-9]{6}", message = "邮政编码长度不对")
    private String aac104;
}
```

* 在验证属性message中使用占位符{xxx}

如上的message出现一个问题，例如`@Range(min = 18, max = 150, message = "年龄范围[18, 150]")`，出现两个18，两个150，
开发的时候，如果改了前面，忘记了后面，就出问题了。
其实可以用占位符`{xxx}`来解决。最终为`@Range(min = 18, max = 150, message = "年龄范围[{min}, {max}]")`


## 对@RequestParam或@PathVariable进行校验

使用@Valid注解，对RequestParam和PathVariable对应的参数进行注解，是无效的，需要使用@Validated注解对类进行注解，来使得验证生效，
然后才能在参数上使用校验注解。

```java
@Validated                         // <------------------- 1
@RequestMapping("/ab01s")
@RestController
public class Ab01Controller {
    @GetMapping("/{aab001}")
    public Ab01 getAc01(@PathVariable 
                            @Min(value = 1, message = "aab001不能小于1")      // <-------------- 2
                            final int aab001) {
        return ab01Service.get(aab001);
    }
}
```

## 自定义校验器

* [Mobile.java](src/main/java/com/example/lewjun/jsr/custom/Mobile.java)
```java
package com.example.lewjun.jsr.custom;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {MobileValidator.class})
public @interface Mobile {
    boolean required() default true;

    String message() default "电话号码格式错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
```

* [MobileValidator.java](src/main/java/com/example/lewjun/jsr/custom/MobileValidator.java)
```java
public class MobileValidator implements ConstraintValidator<Mobile, String> {
    public static final String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";

    private boolean required;

    @Override
    public void initialize(final Mobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(final String s, final ConstraintValidatorContext constraintValidatorContext) {
        if (required) return isMobile(s);
        if (s == null) return true;
        return isMobile(s);
    }


    private boolean isMobile(final String phone) {
        if (phone.length() != 11) {
            return false;
        } else {
            final Pattern p = Pattern.compile(regex);
            final Matcher m = p.matcher(phone);
            return m.matches();
        }
    }
}
```

* 使用

```java

    /**
     * 电话号码
     */
    @Mobile(message = "电话号码格式错误")
    private String aac105;
```

## [application.yml](src/main/resources/application.yml)

```yaml
server:
  port: 1234
  servlet:
    context-path: /demo
```

## Try it

* MacOS/Linux
    * ./mvnw spring-boot:run

* Windows
    * mvnw spring-boot:run

## package

mvn clean package -Dmaven.test.skip=true

## run jar

java -jar xxx.jar

## postman文件

[spring-boot-restfull.postman_collection.json](doc/spring-boot-restfull.postman_collection.json)