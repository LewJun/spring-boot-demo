# where 的使用

> 有时候，所有的查询条件(criteria)应该是可选的。在需要使用至少一种查询条件的情况下，我们应该使用 WHERE 子句。并且， 如果有多个条件，我们需要在条件中添加AND或OR。MyBatis提供了<where>元素支持这种类型的动 态 SQL 语句。

## sql
```xml
    <!--    where 的使用 -->
    <select id="queryWhere" parameterType="com.example.lewjun.domain.Ab01" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from ab01
        <where>
            <if test="aab001!=null">
                and aab001=#{aab001}       <-------- and
            </if>

            <if test="aab002!=null">
                and aab002=#{aab002}
            </if>

            <if test="aab003!=null">
                or aab003=#{aab003}        <---------- or
            </if>
        </where>
    </select>
```

## 测试

```java

@Slf4j
@SpringBootTest
public class Ab01MapperTest {
    @Autowired
    private Ab01Mapper ab01Mapper;

    @Test
    public void testQueryWhere() {
        log.info("【queryWhere:{}】",
                ab01Mapper.queryWhere(
                        new Ab01()
//                                .setAab001(10)
//                                .setAab002("aab002")
//                                .setAab003("aab003")
                )
        );
    }
}
```

### 不传递任何参数
```log
c.e.lewjun.mapper.Ab01Mapper.queryWhere  : ==>  Preparing: select aab001, aab002, aab003 from ab01 
c.e.lewjun.mapper.Ab01Mapper.queryWhere  : ==> Parameters: 
c.e.lewjun.mapper.Ab01Mapper.queryWhere  : <==      Total: 4
```
sql将不会生成where


### 传递 aab001

```log

c.e.lewjun.mapper.Ab01Mapper.queryWhere  : ==>  Preparing: select aab001, aab002, aab003 from ab01 WHERE aab001=? 
c.e.lewjun.mapper.Ab01Mapper.queryWhere  : ==> Parameters: 10(Integer)
c.e.lewjun.mapper.Ab01Mapper.queryWhere  : <==      Total: 1
```
sql会生成where aab001=?

### 传递 aab001 aab002

```log
c.e.lewjun.mapper.Ab01Mapper.queryWhere  : ==>  Preparing: select aab001, aab002, aab003 from ab01 WHERE aab001=? and aab002=? 
c.e.lewjun.mapper.Ab01Mapper.queryWhere  : ==> Parameters: 10(Integer), aab002(String)
c.e.lewjun.mapper.Ab01Mapper.queryWhere  : <==      Total: 0
```
sql会生成where aab001=? and aab002=?

### 传递 aab003
```log
c.e.lewjun.mapper.Ab01Mapper.queryWhere  : ==>  Preparing: select aab001, aab002, aab003 from ab01 WHERE aab003=? 
c.e.lewjun.mapper.Ab01Mapper.queryWhere  : ==> Parameters: aab003(String)
c.e.lewjun.mapper.Ab01Mapper.queryWhere  : <==      Total: 0
```
sql会生成where aab003=?

### 传递 aab001 aab002 aab003

```log
c.e.lewjun.mapper.Ab01Mapper.queryWhere  : ==>  Preparing: select aab001, aab002, aab003 from ab01 WHERE aab001=? and aab002=? or aab003=? 
c.e.lewjun.mapper.Ab01Mapper.queryWhere  : ==> Parameters: 10(Integer), aab002(String), aab003(String)
c.e.lewjun.mapper.Ab01Mapper.queryWhere  : <==      Total: 0
```
sql会生成WHERE aab001=? and aab002=? or aab003=? 

## 总结
where会自动判断
* 如果有符合条件的情况，会自动生成where
* 会自动添加或删除第一个and或or
