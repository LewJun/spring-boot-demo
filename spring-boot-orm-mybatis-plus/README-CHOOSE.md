# choose,when和otherwise条件
> 相当于 if elseif else

* sql
```xml
    <select id="queryChoose" parameterType="string" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from ab01
        <where>
            <choose>
                <when test="words=='a'">
                    and aab001=10
                </when>
                <otherwise>
                    and aab001=50
                </otherwise>
            </choose>
        </where>
    </select>
```

* queryChoose 传递一个String类型的words参数
```java
List<Ab01> queryChoose(String words);
```

* 测试
```java
    @Test
    public void testQueryChoose() {
        log.info("【queryChoose a:{}】",
                ab01Mapper.queryChoose("a")
        );
        log.info("【queryChoose b:{}】",
                ab01Mapper.queryChoose("b")
        );
        log.info("【queryChoose c:{}】",
                ab01Mapper.queryChoose("c")
        );
    }
```

居然报错了
```log
org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.exceptions.PersistenceException: 
### Error querying database.  Cause: java.lang.NumberFormatException: For input string: "a"
### Cause: java.lang.NumberFormatException: For input string: "a"
```

toString()来解决

```xml
    <select id="queryChoose" parameterType="string" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from ab01
        <where>
            <choose>
                <when test="words=='a'.toString()">  <--------'a'.toString()
                    and aab001=10
                </when>
                <when test="words=='b'.toString()">  <--------'b'.toString()
                    and aab001=20
                </when>
                <otherwise>
                    and aab001=50
                </otherwise>
            </choose>
        </where>
    </select>
```

如上问题，不仅限于choose标签，其它动态sql标签在对string进行处理时也会出现同样的问题。
