# foreach 循环

> 它可以迭代遍历一个数组或者列表，构造 AND/OR 条件或 一个 IN 子句。

* queryByPks

```java
List<Ab01> queryByPks(List<Integer> pks);
```

* sql

```xml
    <select id="queryByPks" resultMap="BaseResultMap">
        select * from ab01
        <where>
            <if test="list!=null">
                aab001 in
                <foreach collection="list" item="pk" open="(" close=")" separator=",">
                    #{pk}
                </foreach>
            </if>
        </where>
    </select>
```

```log
c.e.lewjun.mapper.Ab01Mapper.queryByPks  : ==>  Preparing: select * from ab01 WHERE aab001 in ( ? , ? ) 
c.e.lewjun.mapper.Ab01Mapper.queryByPks  : ==> Parameters: 10(Integer), 30(Integer)
c.e.lewjun.mapper.Ab01Mapper.queryByPks  : <==      Total: 2
```

## pks & list & collection
```xml
    <select id="queryByPks" resultMap="BaseResultMap">
        select * from ab01
        <where>
            <if test="pks!=null">     <------- 错误的写法 xxx
                aab001 in
                <foreach collection="pks" item="pk" open="(" close=")" separator=","> <------ 错误的写法 xxx
                    #{pk}
                </foreach>
            </if>
        </where>
    </select>
```
虽然参数名为pks，但是，在sql文件中却要写为list或者collection，否则会报错

```log
org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.binding.BindingException: Parameter 'pks' not found. Available parameters are [collection, list]
```

## 更多例子

* 使用foreach批量插入

```xml
    <insert id="inserts" parameterType="list">
        insert into Ab01(aab001, aab002, aab003) values
        <foreach collection="list" separator="," item="item">
            (#{item.aab001}, #{item.aab002}, #{item.aab003})
        </foreach>
    </insert>
```