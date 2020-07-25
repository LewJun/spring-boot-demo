# trim条件

> `<trim>`元素和`<where>`元素类似，但是`<trim>`提供了在添加前缀/后缀 或者 移除前缀/后缀方面提供更大的灵活
性。

* sql

```xml
    <!--
    这里如果任意一个<if>条件为 true，<trim>元素会插入 WHERE,并且移除紧跟 WHERE 后面的 AND 或 OR
    -->
    <select id="queryTrim" resultMap="BaseResultMap">
        select * from ab01
        <trim prefix="where" prefixOverrides="AND | OR">
            <if test="1==1">
                and aab001=10
            </if>

            <if test="2==2">
                and aab002='xyz'
            </if>
        </trim>
```
