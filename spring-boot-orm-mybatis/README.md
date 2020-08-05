# spring-boot-orm-mybatis

> 一个spring boot整合mybatis的例子

[TOC]


## 添加mybatis依赖

```xml
    <dependencies>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.1.3</version>
        </dependency>

        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
    </dependencies>
```

## 数据源配置
```yaml
# DataSource Config
spring:
  datasource:
    driver-class-name: org.h2.Driver
    schema: classpath:db/schema-h2.sql
    data: classpath:db/data-h2.sql
    url: jdbc:h2:mem:test
    username: root
    password: test

mybatis:
  # 扫描xml的位置
  mapper-locations: classpath:/mappers/**/*Mapper.xml
```

## mybatis 配置
用于扫描mapper的位置 
```java

/**
 * Mybatis 配置
 */
@Configuration
@MapperScan(basePackages = {"com.example.lewjun.mapper"})
@EnableTransactionManagement
public class MybatisConfig {
}
```

## 使用注解完成简单的增删改查

[Ab01Mapper.java](src/main/java/com/example/lewjun/mapper/Ab01Mapper.java)

```java
package com.example.lewjun.mapper;

import com.example.lewjun.domain.Ab01;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Ab01Mapper {

    @Insert("insert into ab01(aab002, aab003) values(#{aab002}, #{aab003})")
    // 自动生成主键
    @Options(useGeneratedKeys = true, keyColumn = "aab001", keyProperty = "aab001")
    int insert(Ab01 ab01);

    @Update("update ab01 set aab002=#{aab002}, aab003=#{aab003} where aab001=#{aab001}")
    int update(Ab01 ab01);

    @Delete("delete from ab01 where aab001=#{aab001}")
    int delete(Integer aab001);

    @Select("select * from ab01 where aab001=#{aab001}")
    Ab01 queryByAab001(Integer aab001);

    @Select("select * from ab01 where aab002=#{aab002}")
    List<Ab01> queryByAab002(String aab002);

    @Select("select * from ab01 where aab002=#{aab002} and aab003=#{aab003}")
    List<Ab01> queryByAab002AndAab003(String aab002, String aab003);

    @Select("select * from ab01")
    @ResultMap("com.example.lewjun.mapper.Ab01Mapper.BaseResultMap")
    List<Ab01> queryAll();

    @Select("select aab001 as ab01_aab001, aab002 as ab01_aab002, aab003 as ab01_aab003 from ab01 where aab003=#{aab003} and aab002=#{aab002}")
    @Results(
            {
                    @Result(id = true, column = "ab01_aab001", property = "aab001")
                    , @Result(column = "ab01_aab002", property = "aab002")
                    , @Result(column = "ab01_aab003", property = "aab003")
            }
    )
    List<Ab01> queryByAab003AndAab002(String aab003, String aab002);

}
```

## 单元测试

```java

@Slf4j
@SpringBootTest
public class Ab01MapperTest {
    @Autowired
    private Ab01Mapper ab01Mapper;

    @Test
    public void testLoadContext() {
        log.info("【queryAll:{}】", ab01Mapper.queryAll());
        log.info("【queryByAab002:{}】", ab01Mapper.queryByAab002("ACCOUNTING"));
        final Ab01 ab01 = new Ab01().setAab002("aab002").setAab003("aab003");
        ab01Mapper.insert(ab01);

        ab01Mapper.update(new Ab01().setAab001(41).setAab002("002").setAab003("003"));

        ab01Mapper.delete(10);

        log.info("【queryByAac001:{}】", ab01Mapper.queryByAab001(20));

        log.info("【queryByAab002AndAab003:{}】", ab01Mapper.queryByAab002AndAab003("002", "003"));

        log.info("【queryByAab003AndAab002:{}】", ab01Mapper.queryByAab003AndAab002("003", "002"));

        log.info("【queryAll:{}】", ab01Mapper.queryAll());
    }
}
```

## 开启mybatis 缓存

[开启mybatis 缓存](README-MYBATIS-CACHE.md)

## Try it

* MacOS/Linux
    * ./mvnw spring-boot:run

* Windows
    * mvnw spring-boot:run

## package

mvn clean package -Dmaven.test.skip=true

## run jar

java -jar xxx.jar

