## 动态sql

> 有 时 候 我 们 需 要 根 据 输 入 条 件 动 态 地 构 建 SQL 语 句 。 MyBatis 提 供 了 各 种 注 解 如 @InsertProvider,@UpdateProvider,@DeleteProvider 和@SelectProvider，来帮助构建动态 SQL 语句，然后让 MyBatis 执行这些 SQL 语句。

[TOC]

* Mapper

[Ab01Mapper.java](src/main/java/com/example/lewjun/mapper/Ab01Mapper.java)
```java
    @SelectProvider(type = Ab01MapperProvider.class, method = "queryByAab001UseProvider")
    Ab01 queryByAab001UseProvider(int aab001);

    @InsertProvider(type = Ab01MapperProvider.class, method = "insertUseProvider")
    int insertUseProvider(Ab01 ab01);

    @UpdateProvider(type = Ab01MapperProvider.class, method = "updateUseProvider")
    int updateUseProvider(Ab01 ab01);

    @UpdateProvider(type = Ab01MapperProvider.class, method = "updateSelectiveProvider")
    int updateSelectiveUseProvider(Ab01 ab01);

    @DeleteProvider(type = Ab01MapperProvider.class, method = "deleteUseProvider")
    int deleteUseProvider(int aab001);

    @SelectProvider(type = Ab01MapperProvider.class, method = "queryByAb01Ac01UseProvider")
    @ResultMap("com.example.lewjun.mapper.Ab01Mapper.Ab01Ac01")
    List<Ab01Ac01> queryByAb01Ac01UseProvider();
```

* Provider

[Ab01MapperProvider.java](src/main/java/com/example/lewjun/mapper/Ab01MapperProvider.java)

```java

package com.example.lewjun.mapper;

import com.example.lewjun.domain.Ab01;
import org.apache.ibatis.jdbc.SQL;

public class Ab01MapperProvider {
    public String queryByAab001UseProvider(final int aab001) {
        return new SQL() {
            {
//                SELECT("aab001, aab002, aab003");
                SELECT("aab001");
                SELECT("aab002");
                SELECT("aab003");
                FROM("ab01");
                if (aab001 > 0) {
                    WHERE("aab001=#{aab001}");
                } else {
                    WHERE("1=2");
                }
            }
        }.toString();
    }

    public String insertUseProvider(final Ab01 ab01) {
        return new SQL() {
            {
                INSERT_INTO("ab01");
                if (ab01.getAab002() != null) {
                    VALUES("aab002", ab01.getAab002());
                }

                if (ab01.getAab003() != null) {
                    VALUES("aab003", ab01.getAab003());
                }
            }
        }.toString();
    }

    public String updateUseProvider(final Ab01 ab01) {
        return new SQL() {
            {
                UPDATE("ab01");
                SET("aab002 = #{aab002}");
                SET("aab003 = #{aab003}");
                WHERE("aab001 = #{aab001}");
            }
        }.toString();
    }

    public String updateSelectiveProvider(final Ab01 ab01) {
        return new SQL() {
            {
                UPDATE("ab01");
                if (ab01.getAab002() != null) {
                    SET("aab002=#{aab002}");
                }

                if (ab01.getAab003() != null) {
                    SET("aab003=#{aab003}");
                }

                WHERE("aab001=#{aab001}");
            }
        }.toString();
    }

    public String deleteUseProvider(final int aab001) {
        return new SQL() {
            {
                DELETE_FROM("ab01");
                WHERE("aab001=#{aab001}");
            }
        }.toString();
    }

    public String queryByAb01Ac01UseProvider() {
        return new SQL() {
            {
                SELECT("ab01.*");
                SELECT("ac01.*");
                FROM("ab01 as ab01");
                LEFT_OUTER_JOIN("ac01 as ac01 on ac01.aac006 = ab01.aab001");
                ORDER_BY("ab01.aab001 desc");
            }
        }.toString();
    }
}
```


* 单元测试

[Ab01MapperTest.java](src/test/java/com/example/lewjun/Ab01MapperTest.java)

```java
    @Test
    public void testInsertUseProvider() {
        final Ab01 ab01 = new Ab01().setAab002("002").setAab003("003");
        log.info("【insertUseProvider: {}】", ab01Mapper.insertUseProvider(ab01));
    }

    @Test
    public void testUpdateUseProvider() {
        log.info("【updateUseProvider: {}】", ab01Mapper.updateUseProvider(
                new Ab01().setAab001(10)
                        .setAab002("String aab002")
                        .setAab003("String aab003")
                )
        );

        log.info("【queryByAab001: {}】", ab01Mapper.queryByAab001(10));
    }

    @Test
    public void testUpdateSelectiveUseProvider() {
        log.info("【updateSelectiveUseProvider: {}】", ab01Mapper.updateSelectiveUseProvider(
                new Ab01().setAab001(10)
                        .setAab002("String aab002")
                )
        );

        log.info("【queryByAab001: {}】", ab01Mapper.queryByAab001(10));
    }

    @Test
    public void testDeleteUseProvider() {
        log.info("【deleteUseProvider: {}】", ab01Mapper.deleteUseProvider(10));

        log.info("【queryAll: {}】", ab01Mapper.queryAll());
    }

    @Test
    public void testQueryByAab001UseProvider() {
        log.info("【queryByAab001UseProvider: {}】", ab01Mapper.queryByAab001UseProvider(10));
    }

    @Test
    public void testQueryByAb01Ac01UseProvider() {
        log.info("【queryByAb01Ac01UseProvider: {}】", ab01Mapper.queryByAb01Ac01UseProvider());
    }
```