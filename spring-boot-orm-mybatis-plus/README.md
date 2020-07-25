# spring-boot-orm-mybatis-plus

> spring boot 集成 mybatis-plus

[TOC]

## 加入mybatis-plus依赖和h2数据库驱动

```xml
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
        </dependency>

        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
```

## 配置h2数据库的相关

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
```

## Ab01

[Ab01.java](src/main/java/com/example/lewjun/domain/Ab01.java)
```java
@Data
@Builder
@TableName("ab01")
public class Ab01 extends BaseObj {
    /**
     * 部门编号 如果不是id字段，那么需要使用@TableId注释这就是id
     */
    @TableId
    private Integer aab001;
    /**
     * 部门名称
     */
    private String aab002;
    /**
     * 部门所在位置
     */
    private String aab003;
}
```

## Ab01Mapper

[Ab01Mapper.java](src/main/java/com/example/lewjun/mapper/Ab01Mapper.java)

```java
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.lewjun.domain.Ab01;
import org.springframework.stereotype.Repository;

@Repository
public interface Ab01Mapper extends BaseMapper<Ab01> {
}
```

## Ab01Service

```java
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.lewjun.domain.Ab01;

public interface Ab01Service extends IService<Ab01> {
}
```

## Ab01ServiceImpl

```java
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.lewjun.domain.Ab01;
import com.example.lewjun.mapper.Ab01Mapper;
import com.example.lewjun.service.Ab01Service;
import org.springframework.stereotype.Service;

@Service
public class Ab01ServiceImpl extends ServiceImpl<Ab01Mapper, Ab01> implements Ab01Service {
}
```

## 扫描Mapper配置

[MybatisPlusConfig.java](src/main/java/com/example/lewjun/config/MybatisPlusConfig.java)

```java
/**
 * Mybatis plus 配置
 */
@Configuration
@MapperScan(basePackages = {"com.example.lewjun.mapper"})
@EnableTransactionManagement
public class MybatisPlusConfig {
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
```

## 单元测试

```java
@Slf4j
@SpringBootTest
public class Ab01ServiceTest {
    @Autowired
    private Ab01Service ab01Service;

    @Test
    public void testAb01Service() {
        // 增
        final Ab01 ab01Save = new Ab01(50, "aab002", "aab003");
        if (ab01Service.save(ab01Save)) {
            log.info("【list: {}】", ab01Service.list());
        }

        log.info("【saveBatch: {}】",
                ab01Service.saveBatch(
                        Arrays.asList(
                                new Ab01(1, "a1", "b1"),
                                new Ab01(2, "a2", "b2"),
                                new Ab01(3, "a3", "b3")
                        )
                ));

        final int count = ab01Service.count();
        log.info("【count: {}】", count);

        // 根据id查询
        final Ab01 ab01 = ab01Service.getById(10);
        log.info("【ab01: {}】", ab01);

        // 修改
        ab01Service.updateById(ab01.setAab002("xxx").setAab003("yyy"));

        // 删除
        log.info("【remove 60: {}】", ab01Service.removeById(60));
        log.info("【remove 50: {}】", ab01Service.removeById(50));

        // 获取列表
        log.info("【list: {}】", ab01Service.list());

        // list by ids
        log.info("【listByIds: {}】", ab01Service.listByIds(Arrays.asList(20, 30, 40, 50, 60)));

        log.info("【aab002=b2: {}】", ab01Service.query().eq("aab002", "a2").count());

        log.info("【aab003 like ll: {}】", ab01Service.query().like("aab003", "LL").list());

        Page<Ab01> ab01Page = ab01Service.page(
                new Page<Ab01>()
                        .setCurrent(2) // 第2页
                        .setSize(3) // 第3页
        );

        // 按照条件，总的有7条
        log.info("【ab01Page total: {}】", ab01Page.getTotal());

        final List<Ab01> ab01List = ab01Page.getRecords();

        // 第2页的3条数据
        log.info("【ab01List page: {}】", ab01List);

        log.info("【ab01 page 3/3: {}】",
                ab01Service.page(
                        new Page<Ab01>()
                                .setCurrent(3) // 第3页
                                .setSize(3) // 第3页
                ).getRecords()
        );
    }
}
```

## 使用注解查询

```java
@Repository
public interface Ab01Mapper extends BaseMapper<Ab01> {
    @Select("select * from ab01 where aab002=#{aab002}")
    List<Ab01> queryByAab002(String aab002);
}
```

## 使用xml方式查询

* 完全使用注解可以吗？

* 配置扫描xml的位置

```yaml
mybatis-plus:
  # 扫描xml的位置
  mapper-locations: classpath:/mappers/**/*Mapper.xml
  configuration:
    cache-enabled: false
```

* [Ab01Mapper.xml](src/main/resources/mappers/com/example/lewjun/mapper/Ab01Mapper.xml)

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.example.lewjun.mapper.Ab01Mapper">
    <select id="queryByAab003" resultType="com.example.lewjun.domain.Ab01">
        select * from ab01 where aab003=#{aab003}
    </select>
</mapper>
```

## 1对多查询

```xml
    <select id="queryAb01Ac01" resultMap="Ab01Ac01">
        select ab01.*, ac01.* from ab01
        left join ac01 on ab01.aab001=ac01.aac006
    </select>

    <resultMap id="Ab01Ac01" type="com.example.lewjun.domain.Ab01Ac01" extends="BaseResultMap">
        <collection property="ac01s" resultMap="com.example.lewjun.mapper.Ac01Mapper.BaseResultMap"/>
    </resultMap>
```

结果运行的时候一直报错
```log
org.springframework.dao.DataIntegrityViolationException: Error attempting to get column 'AAC002' from result set.  Cause: java.sql.SQLDataException: Cannot convert string 'SMITH' to java.sql.Timestamp value
; Cannot convert string 'SMITH' to java.sql.Timestamp value; nested exception is java.sql.SQLDataException: Cannot convert string 'SMITH' to java.sql.Timestamp value
```
几近曲折，最后把Ac01上的@Builder去掉就可以了。

原因是：@Builder导致无参的构造方法不在了。也可以加上

@NoArgsConstructor
@AllArgsConstructor
@Builder

## 动态sql

* [where 的使用](README-WHERE.md)
* [if 条件](README-IF.md)
* [choose,when和otherwise条件](README-CHOOSE.md)

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