# set 条件

> `<set>`元素和`<where>`元素类似，如果其内部条件判断有任何内容返回时，会插入 SET SQL 片段。

```xml
    <update id="updateSelective" parameterType="com.example.lewjun.domain.Ab01">
        update ab01
        <set>
            <if test="aab002!=null">
                aab002=#{aab002},
            </if>
            <if test="aab003!=null">
                aab003=#{aab003},
            </if>
        </set>
        where aab001=#{aab001}
    </update>
```

* 单元测试

```java
    @Test
    public void testUpdateSelective() {
        log.info("【updateSelective:{}】", ab01Mapper.updateSelective(new Ab01(10, null, "aab003")));
        log.info("【selectById:{}】", ab01Mapper.selectById(10));
    }
```
传递的aab002为null

* 输出

```log
c.e.l.mapper.Ab01Mapper.updateSelective  : ==>  Preparing: update ab01 SET aab003=? where aab001=? 
c.e.l.mapper.Ab01Mapper.updateSelective  : ==> Parameters: aab003(String), 10(Integer)
c.e.l.mapper.Ab01Mapper.updateSelective  : <==    Updates: 1
com.example.lewjun.Ab01MapperTest        : 【updateSelective:1】
c.e.lewjun.mapper.Ab01Mapper.selectById  : ==>  Preparing: SELECT aab001,aab002,aab003 FROM ab01 WHERE aab001=? 
c.e.lewjun.mapper.Ab01Mapper.selectById  : ==> Parameters: 10(Integer)
c.e.lewjun.mapper.Ab01Mapper.selectById  : <==      Total: 1
com.example.lewjun.Ab01MapperTest        : 【selectById:Ab01(aab001=10, aab002=ACCOUNTING, aab003=aab003)】
```

